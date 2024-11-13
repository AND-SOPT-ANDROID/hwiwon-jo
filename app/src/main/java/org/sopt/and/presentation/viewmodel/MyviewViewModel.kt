package org.sopt.and.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.data.repository.MyviewRepository
import org.sopt.and.data.repository.SharedPreferencesHelper
import javax.inject.Inject

@HiltViewModel
class MyviewViewModel @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val myviewRepository: MyviewRepository
) : ViewModel() {
    private val _hobbyResult = MutableLiveData<Result<String>>()
    val hobbyResult: LiveData<Result<String>> get() = _hobbyResult

    fun getHobby() {
        viewModelScope.launch {
            val token = getTokenForRequest()
            if (token != null) {
                val result = myviewRepository.getHobby(token)
                _hobbyResult.postValue(result)
            } else {
                _hobbyResult.postValue(Result.failure(Exception("토큰이 없습니다.")))
            }
        }
    }

    fun getTokenForRequest(): String? {
        return sharedPreferencesHelper.getToken()
    }
}