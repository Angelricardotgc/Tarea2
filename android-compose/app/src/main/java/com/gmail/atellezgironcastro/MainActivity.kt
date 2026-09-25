package com.gmail.atellezgironcastro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gmail.atellezgironcastro.navigation.AppNavigation
import com.gmail.atellezgironcastro.ui.theme.CatalogoUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogoUITheme {
                AppNavigation()
            }
        }
    }
}