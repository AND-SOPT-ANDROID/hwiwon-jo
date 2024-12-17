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
import org.sopt.and.data.repositoryimpl.UserRegistrationRepositoryImpl
import org.sopt.and.domain.entity.UserEntity
import org.sopt.and.domain.entity.UserNoEntity
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRegistrationRepository: UserRegistrationRepositoryImpl
) : ViewModel() {

    private val _userRegistrationResult = MutableLiveData<Result<UserNoEntity>>()
    val userRegistrationResult: LiveData<Result<UserNoEntity>> get() = _userRegistrationResult

    fun registerUser(username: String, password: String, hobby: String) {
        viewModelScope.launch {
            val userRequest = UserEntity(username, password, hobby)
            val result = userRegistrationRepository.registerUser(userRequest)
            _userRegistrationResult.postValue(result)
        }
    }

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var hobby by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    fun validateSignUp(username: String, password: String, hobby: String): Boolean {
        return username.length <= 8 && password.length <= 8 && hobby.length <= 8
    }
}

