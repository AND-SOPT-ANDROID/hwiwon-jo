package org.sopt.and.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.repositoryimpl.SharedPreferencesHelper
import org.sopt.and.domain.entity.LoginEntity
import org.sopt.and.domain.repository.LoginRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect: SharedFlow<SignInSideEffect> = _sideEffect

    fun processIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.EnterUsername -> updateState { it.copy(email = intent.username) }
            is SignInIntent.EnterPassword -> updateState { it.copy(password = intent.password) }
            is SignInIntent.TogglePasswordVisibility -> togglePasswordVisibility()
            is SignInIntent.SubmitSignIn -> postLogin()
        }
    }


    private fun togglePasswordVisibility() {
        updateState { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun postLogin() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, errorMessage = null) }
            val loginEntity =
                LoginEntity(username = state.value.email, password = state.value.password)
            val result = loginRepository.postLogin(loginEntity)

            result.onSuccess { tokenEntity ->
                sharedPreferencesHelper.saveToken(tokenEntity.token)
                updateState { it.copy(isLoading = false, isSuccess = true) }
                _sideEffect.emit(SignInSideEffect.NavigateToMain)
            }.onFailure { exception ->
                updateState { it.copy(isLoading = false, errorMessage = exception.message) }
                _sideEffect.emit(SignInSideEffect.ShowToast("로그인 실패: ${exception.message}"))
            }
        }
    }

    private fun updateState(update: (SignInState) -> SignInState) {
        _state.value = update(_state.value)
    }

}
