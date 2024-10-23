package org.sopt.and.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyviewViewModel : ViewModel() {
    var email by mutableStateOf("")
    
}