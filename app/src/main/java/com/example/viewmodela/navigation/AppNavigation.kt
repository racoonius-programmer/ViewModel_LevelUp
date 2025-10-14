package com.example.viewmodela.navigation

import ResumenScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viewmodela.ui.screen.RegistroScreen
import com.example.viewmodela.ui.screen.InicioScreen
import com.example.viewmodela.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    //Aquí creamos el ViewModel una vez
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ){
        composable("inicio"){
            InicioScreen(navController)
        }
        composable("registro"){
            RegistroScreen(navController, usuarioViewModel)
        }
        composable("resumen"){
            ResumenScreen(usuarioViewModel)
        }
    }
}