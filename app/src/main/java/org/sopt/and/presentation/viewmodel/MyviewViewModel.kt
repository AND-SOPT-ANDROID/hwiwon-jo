package org.sopt.and.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.sopt.and.data.repositoryimpl.SharedPreferencesHelper
import org.sopt.and.domain.entity.HobbyEntity
import org.sopt.and.domain.repository.MyviewRepository
import javax.inject.Inject

@HiltViewModel
class MyviewViewModel @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val myviewRepository: MyviewRepository
) : ViewModel() {
    private val _hobbyResult = MutableLiveData<Result<HobbyEntity>>()
    val hobbyResult: LiveData<Result<HobbyEntity>> get() = _hobbyResult

    fun getHobby() {
        viewModelScope.launch {
            val token = getTokenForRequest()
            if (token != null) {
                val result = withContext(Dispatchers.IO) {
                    myviewRepository.getHobby(token)
                }
                _hobbyResult.postValue(result)
            } else {
                _hobbyResult.postValue(Result.failure(Exception("토큰이 없습니다.")))
            }
        }
    }

    private fun getTokenForRequest(): String? {
        return sharedPreferencesHelper.getToken()
    }
}