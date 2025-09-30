package com.example.viewmodela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.viewmodela.navigation.AppNavigation
import com.example.viewmodela.ui.theme.ViewModelATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewModelATheme {
                Scaffold { innerPadding ->
                    //Aqui va el flujo de navegación
                    Box(modifier = Modifier.padding(innerPadding))
                    AppNavigation()
                }
            }
        }
    }
}

