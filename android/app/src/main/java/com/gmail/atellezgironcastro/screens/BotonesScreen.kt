package com.gmail.atellezgironcastro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BotonesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sección 2: Botones y Acciones", style = MaterialTheme.typography.headlineSmall)

        // 1. Botón relleno, con contorno y de solo texto
        ElementoDemo(
            nombre = "Botón relleno, con contorno y de texto",
            explicacion = "Tres niveles de énfasis visual: relleno para la acción principal, con contorno para una acción secundaria, y de solo texto para acciones de menor importancia."
        ) {
            var contador by remember { mutableStateOf(0) }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { contador++ }) { Text("Botón relleno") }
                OutlinedButton(onClick = { contador++ }) { Text("Botón con contorno") }
                TextButton(onClick = { contador++ }) { Text("Botón de texto") }
                Text("Veces presionado: $contador", style = MaterialTheme.typography.bodySmall)
            }
        }

        // 2. Botón con ícono (solo ícono y con ícono+texto)
        ElementoDemo(
            nombre = "Botón con ícono",
            explicacion = "Puede mostrar solo un ícono (para acciones obvias y ahorrar espacio) o combinar ícono con texto (para mayor claridad)."
        ) {
            var favoritos by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IconButton(onClick = { favoritos++ }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorito")
                }
                Button(onClick = { favoritos++ }) {
                    Icon(Icons.Filled.Favorite, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Me gusta")
                }
                Text("$favoritos", style = MaterialTheme.typography.bodySmall)
            }
        }

        // 3. Botón de acción flotante (normal y extendido)
        ElementoDemo(
            nombre = "Botón de acción flotante (FAB)",
            explicacion = "Botón circular que flota sobre el contenido, usado para la acción principal de una pantalla. Existe en versión normal (solo ícono) y extendida (ícono + texto)."
        ) {
            var toques by remember { mutableStateOf(0) }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                FloatingActionButton(onClick = { toques++ }) {
                    Icon(Icons.Filled.Add, contentDescription = "Agregar")
                }
                ExtendedFloatingActionButton(
                    onClick = { toques++ },
                    icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                    text = { Text("Agregar nuevo") }
                )
            }
            Text("Toques: $toques", style = MaterialTheme.typography.bodySmall)
        }

        // 4. Botón de alternancia / selector segmentado
        ElementoDemo(
            nombre = "Botón de alternancia (toggle)",
            explicacion = "Permite elegir entre varias opciones mutuamente excluyentes, mostrando cuál está activa en todo momento."
        ) {
            val opciones = listOf("Día", "Semana", "Mes")
            var seleccionado by remember { mutableStateOf(0) }
            SingleChoiceSegmentedButtonRow {
                opciones.forEachIndexed { indice, texto ->
                    SegmentedButton(
                        selected = seleccionado == indice,
                        onClick = { seleccionado = indice },
                        shape = SegmentedButtonDefaults.itemShape(index = indice, count = opciones.size)
                    ) {
                        Text(texto)
                    }
                }
            }
        }

        // 5. Botón deshabilitado y botón de carga
        ElementoDemo(
            nombre = "Botón deshabilitado y botón de carga",
            explicacion = "El botón deshabilitado no responde a toques (indica que la acción no está disponible). El botón de carga muestra un indicador mientras procesa una acción."
        ) {
            var cargando by remember { mutableStateOf(false) }
            val alcance = rememberCoroutineScope()
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {}, enabled = false) { Text("Botón deshabilitado") }
                Button(
                    onClick = {
                        alcance.launch {
                            cargando = true
                            delay(2000)
                            cargando = false
                        }
                    },
                    enabled = !cargando
                ) {
                    if (cargando) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Guardar (toca para simular carga)")
                    }
                }
            }
        }
    }
}