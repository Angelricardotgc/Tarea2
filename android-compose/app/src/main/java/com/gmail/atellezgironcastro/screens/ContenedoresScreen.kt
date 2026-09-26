package com.gmail.atellezgironcastro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenedoresScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sección 6: Contenedores y Estructura", style = MaterialTheme.typography.headlineSmall)

        // 1. Fila, columna y superposición
        ElementoDemo(
            nombre = "Distribución en fila, columna y superpuesta",
            explicacion = "Row organiza elementos uno junto a otro (horizontal). Column los apila uno debajo de otro (vertical). Box los superpone, uno encima de otro."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Row (fila):", style = MaterialTheme.typography.labelMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(3) { indice ->
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) { Text("${indice + 1}") }
                    }
                }
                Text("Column (columna):", style = MaterialTheme.typography.labelMedium)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(3) { indice ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            contentAlignment = Alignment.Center
                        ) { Text("Fila ${indice + 1}") }
                    }
                }
                Text("Box (superpuesta):", style = MaterialTheme.typography.labelMedium)
                Box(modifier = Modifier.fillMaxWidth().height(80.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.tertiaryContainer)
                    )
                    Text(
                        "Texto encima",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
                    )
                }
            }
        }

        // 2. Contenedor con desplazamiento vertical
        ElementoDemo(
            nombre = "Contenedor con desplazamiento vertical",
            explicacion = "Cuando el contenido es más alto que la pantalla, un contenedor con scroll permite desplazarse para ver todo. Esta misma pantalla completa ya usa esta técnica."
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                Column {
                    repeat(10) { indice ->
                        Text("Línea de contenido número ${indice + 1}", modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        }

        // 3. Barra superior con título y acciones
        ElementoDemo(
            nombre = "Barra superior con título y acciones",
            explicacion = "Encabezado fijo en la parte alta de la pantalla, con el título de la sección actual y accesos rápidos a acciones comunes (buscar, más opciones)."
        ) {
            TopAppBar(
                title = { Text("Mi Aplicación") },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Más opciones")
                    }
                }
            )
        }

        // 4. Barra de navegación inferior o menú lateral
        ElementoDemo(
            nombre = "Barra de navegación inferior / menú lateral",
            explicacion = "Permite moverse entre las secciones principales de la app. Esta misma pantalla usa una barra inferior real (ver la parte de abajo de la app); aquí se muestra un ejemplo de menú lateral (drawer) como alternativa."
        ) {
            var seccionSeleccionada by remember { mutableStateOf(0) }
            val opciones = listOf("Inicio" to Icons.Filled.Home, "Perfil" to Icons.Filled.Person, "Ajustes" to Icons.Filled.Settings)
            Card {
                Column(modifier = Modifier.width(200.dp).padding(vertical = 8.dp)) {
                    opciones.forEachIndexed { indice, (texto, icono) ->
                        NavigationDrawerItem(
                            label = { Text(texto) },
                            icon = { Icon(icono, contentDescription = null) },
                            selected = seccionSeleccionada == indice,
                            onClick = { seccionSeleccionada = indice },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }

        // 5. Distribución con pesos proporcionales
        ElementoDemo(
            nombre = "Distribución con pesos proporcionales (weight)",
            explicacion = "Reparte el espacio disponible entre varios elementos según una proporción, en lugar de un tamaño fijo. Aquí los tres bloques ocupan 1, 2 y 1 partes del ancho total."
        ) {
            Row(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color(0xFFEF9A9A)),
                    contentAlignment = Alignment.Center
                ) { Text("1") }
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .background(Color(0xFF90CAF9)),
                    contentAlignment = Alignment.Center
                ) { Text("2") }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color(0xFFA5D6A7)),
                    contentAlignment = Alignment.Center
                ) { Text("1") }
            }
        }
    }
}