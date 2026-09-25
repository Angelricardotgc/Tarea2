package com.gmail.atellezgironcastro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.atellezgironcastro.navigation.Rutas
import com.gmail.atellezgironcastro.navigation.seccionesNavegacion

@Composable
fun HomeScreen(alSeleccionarSeccion: (Rutas) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Catálogo de Elementos UI",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(seccionesNavegacion) { seccion ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    onClick = { alSeleccionarSeccion(seccion) }
                ) {
                    Box(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                        Text(
                            text = seccion.titulo,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}