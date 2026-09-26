package com.gmail.atellezgironcastro.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gmail.atellezgironcastro.screens.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catálogo de Elementos UI") })
        },
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NavigationBar {
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == Rutas.Inicio.ruta } == true,
                    onClick = {
                        navController.navigate(Rutas.Inicio.ruta) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                seccionesNavegacion.forEach { seccion ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == seccion.ruta } == true,
                        onClick = {
                            navController.navigate(seccion.ruta) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(Icons.Filled.Home, contentDescription = seccion.titulo) },
                        label = { Text(seccion.titulo.take(8)) }
                    )
                }
            }
        }
    ) { paddingInterno ->
        NavHost(
            navController = navController,
            startDestination = Rutas.Inicio.ruta,
            modifier = Modifier.padding(paddingInterno)
        ) {
            composable(Rutas.Inicio.ruta) {
                HomeScreen(alSeleccionarSeccion = { seccion ->
                    navController.navigate(seccion.ruta)
                })
            }
            composable(Rutas.EntradaTexto.ruta) { EntradaTextoScreen() }
            composable(Rutas.Botones.ruta) { BotonesScreen() }
            composable(Rutas.Seleccion.ruta) { SeleccionScreen() }
            composable(Rutas.Listas.ruta) { ListasScreen() }
            composable(Rutas.InfoFeedback.ruta) { InfoFeedbackScreen() }
            composable(Rutas.Contenedores.ruta) { ContenedoresScreen() }
        }
    }
}