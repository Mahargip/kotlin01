package com.maha.kotlin01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.maha.kotlin01.presentation.navigation.AppNavigation
import com.maha.kotlin01.ui.theme.Kotlin01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kotlin01Theme {
                AppNavigation()
            }
        }
    }
}
