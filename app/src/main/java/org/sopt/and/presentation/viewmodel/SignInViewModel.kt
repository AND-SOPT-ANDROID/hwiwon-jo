package org.sopt.and.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.data.repositoryimpl.SharedPreferencesHelper
import org.sopt.and.domain.entity.LoginEntity
import org.sopt.and.domain.entity.TokenEntity
import org.sopt.and.domain.repository.LoginRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<TokenEntity>>()
    val loginResult: LiveData<Result<TokenEntity>> get() = _loginResult

    fun postLogin(username: String, password: String) {
        viewModelScope.launch {
            val loginEntity = LoginEntity(username = username, password = password)
            val result = loginRepository.postLogin(loginEntity)
            result.onSuccess { tokenEntity ->
                saveToken(tokenEntity.token)
            }
            _loginResult.postValue(result)
        }
    }

    fun saveToken(token: String) {
        sharedPreferencesHelper.saveToken(token)
    }

    private var _username by mutableStateOf("")
    val username: String
        get() = _username

    private var _password by mutableStateOf("")
    val password: String
        get() = _password

    fun updateUsernameLogin(username: String) {
        _username = username
    }

    fun updatePasswordLogin(password: String) {
        _password = password
    }

}
