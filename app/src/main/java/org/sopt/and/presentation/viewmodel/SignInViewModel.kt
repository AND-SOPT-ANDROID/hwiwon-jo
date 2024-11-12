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
import org.sopt.and.data.dto.RequestLoginData
import org.sopt.and.data.dto.ResponseLogin
import org.sopt.and.data.repository.Auth.LoginRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<ResponseLogin>>()
    val loginResult: LiveData<Result<ResponseLogin>> get() = _loginResult

    fun postLogin(username: String, password: String) {
        viewModelScope.launch {
            val loginRequest = RequestLoginData(username, password)
            val result = loginRepository.postLogin(loginRequest)
            _loginResult.postValue(result)
        }
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
