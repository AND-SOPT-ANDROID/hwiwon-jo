package org.sopt.and.presentation.viewmodel

import android.util.Log
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
import org.sopt.and.data.repository.SharedPreferencesHelper
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<ResponseLogin>>()
    val loginResult: LiveData<Result<ResponseLogin>> get() = _loginResult

    fun postLogin(username: String, password: String) {
        viewModelScope.launch {
            val loginRequest = RequestLoginData(username, password)
            val result = loginRepository.postLogin(loginRequest)
            result.onSuccess {
                val token = it.result.token
                saveToken(token)
                Log.d("SignInResultViewModel", "토큰이 저장됐을까.. $token")
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
