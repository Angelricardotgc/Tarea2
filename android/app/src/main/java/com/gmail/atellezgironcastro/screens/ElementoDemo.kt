package com.gmail.atellezgironcastro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable reutilizable que envuelve cada elemento del catálogo con:
 * su nombre, una breve explicación, y una demostración interactiva.
 * Se usa en las 6 secciones del catálogo.
 */
@Composable
fun ElementoDemo(nombre: String, explicacion: String, contenido: @Composable () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(nombre, style = MaterialTheme.typography.titleMedium)
            Text(explicacion, style = MaterialTheme.typography.bodySmall)
            contenido()
        }
    }
}