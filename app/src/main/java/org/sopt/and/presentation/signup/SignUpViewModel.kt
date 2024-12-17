package org.sopt.and.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.repositoryimpl.UserRegistrationRepositoryImpl
import org.sopt.and.domain.entity.UserEntity
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRegistrationRepository: UserRegistrationRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect

    fun processIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.EnterUsername -> updateState { copy(email = intent.username) }
            is SignUpIntent.EnterPassword -> updateState { copy(password = intent.password) }
            is SignUpIntent.EnterHobby -> updateState { copy(hobby = intent.hobby) }
            SignUpIntent.TogglePasswordVisibility -> togglePasswordVisibility()
            SignUpIntent.SubmitSignUp -> submitSignUp()
        }
    }

    private fun togglePasswordVisibility() {
        updateState { copy(isPasswordVisible = !isPasswordVisible) }
    }

    private fun submitSignUp() {
        val currentState = _state.value
        if (validateSignUp(currentState.email, currentState.password, currentState.hobby)) {
            registerUser(currentState.email, currentState.password, currentState.hobby)
        } else {
            viewModelScope.launch {
                _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입 조건에 부합하지 않습니다."))
            }
        }
    }

    private fun registerUser(email: String, password: String, hobby: String) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            userRegistrationRepository.registerUser(UserEntity(email, password, hobby))
                .onSuccess {
                    updateState { copy(isLoading = false, isSuccess = true) }
                    _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입 성공!"))
                }
                .onFailure { error ->
                    updateState { copy(isLoading = false, errorMessage = error.message) }
                    _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입 실패: ${error.message}"))
                }
        }
    }

    private fun updateState(update: SignUpState.() -> SignUpState) {
        _state.value = _state.value.update()
    }

    private fun validateSignUp(username: String, password: String, hobby: String): Boolean {
        return username.length <= 8 && password.length <= 8 && hobby.length <= 8
    }
}

