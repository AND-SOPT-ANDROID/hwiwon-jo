package org.sopt.and.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.and.presentation.ui.auth.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUpScreen()
            }
        }
    }
}
