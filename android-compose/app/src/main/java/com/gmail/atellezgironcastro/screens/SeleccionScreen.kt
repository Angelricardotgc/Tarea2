package com.gmail.atellezgironcastro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.state.ToggleableState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sección 3: Elementos de Selección", style = MaterialTheme.typography.headlineSmall)

        // 1. Checkbox con estado indeterminado
        ElementoDemo(
            nombre = "Casilla de verificación",
            explicacion = "Permite marcar opciones independientes. El estado indeterminado se usa cuando algunas (no todas) las sub-opciones están marcadas."
        ) {
            var opcion1 by remember { mutableStateOf(true) }
            var opcion2 by remember { mutableStateOf(false) }
            val todasMarcadas = opcion1 && opcion2
            val algunaMarcada = opcion1 || opcion2
            val estadoPadre = when {
                todasMarcadas -> ToggleableState.On
                algunaMarcada -> ToggleableState.Indeterminate
                else -> ToggleableState.Off
            }
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TriStateCheckbox(
                        state = estadoPadre,
                        onClick = {
                            val nuevoValor = estadoPadre != ToggleableState.On
                            opcion1 = nuevoValor
                            opcion2 = nuevoValor
                        }
                    )
                    Text("Seleccionar todo (indeterminado si es parcial)")
                }
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 24.dp)) {
                    Checkbox(checked = opcion1, onCheckedChange = { opcion1 = it })
                    Text("Notificaciones por correo")
                }
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 24.dp)) {
                    Checkbox(checked = opcion2, onCheckedChange = { opcion2 = it })
                    Text("Notificaciones push")
                }
            }
        }

        // 2. Radio buttons mutuamente excluyentes
        ElementoDemo(
            nombre = "Grupo de botones de opción",
            explicacion = "Permite elegir una sola opción entre varias mutuamente excluyentes."
        ) {
            val opciones = listOf("Tarjeta de crédito", "Transferencia", "Efectivo")
            var seleccionado by remember { mutableStateOf(opciones[0]) }
            Column {
                opciones.forEach { opcion ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = seleccionado == opcion,
                            onClick = { seleccionado = opcion }
                        )
                        Text(opcion)
                    }
                }
            }
        }

        // 3. Switch
        ElementoDemo(
            nombre = "Interruptor (switch)",
            explicacion = "Activa o desactiva una opción de forma inmediata, típico para configuraciones on/off."
        ) {
            var activado by remember { mutableStateOf(false) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(checked = activado, onCheckedChange = { activado = it })
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (activado) "Modo oscuro activado" else "Modo oscuro desactivado")
            }
        }

        // 4. Slider simple y de rango
        ElementoDemo(
            nombre = "Deslizador de valor único y de rango",
            explicacion = "Permite seleccionar un valor numérico (o un rango de dos valores) deslizando el dedo sobre una barra."
        ) {
            var valorUnico by remember { mutableStateOf(50f) }
            var rango by remember { mutableStateOf(20f..80f) }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Volumen: ${valorUnico.toInt()}")
                Slider(value = valorUnico, onValueChange = { valorUnico = it }, valueRange = 0f..100f)
                Text("Rango de precio: ${rango.start.toInt()} - ${rango.endInclusive.toInt()}")
                RangeSlider(value = rango, onValueChange = { rango = it }, valueRange = 0f..100f)
            }
        }

        // 5. Lista desplegable de selección
        ElementoDemo(
            nombre = "Lista desplegable de selección",
            explicacion = "Muestra un menú con varias opciones al tocarlo, de las cuales el usuario elige una."
        ) {
            val opciones = listOf("México", "Estados Unidos", "Canadá", "España")
            var seleccionado by remember { mutableStateOf(opciones[0]) }
            var expandido by remember { mutableStateOf(false) }
            Box {
                OutlinedButton(onClick = { expandido = true }) {
                    Text(seleccionado)
                }
                DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
                    opciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                seleccionado = opcion
                                expandido = false
                            }
                        )
                    }
                }
            }
        }

        // 6. Selector de fecha y hora
        ElementoDemo(
            nombre = "Selector de fecha y hora",
            explicacion = "Abre un diálogo nativo para elegir una fecha o una hora específica de forma visual."
        ) {
            var fechaTexto by remember { mutableStateOf("Sin seleccionar") }
            var horaTexto by remember { mutableStateOf("Sin seleccionar") }
            var mostrarDatePicker by remember { mutableStateOf(false) }
            var mostrarTimePicker by remember { mutableStateOf(false) }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { mostrarDatePicker = true }) {
                    Icon(Icons.Filled.DateRange, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Fecha: $fechaTexto")
                }
                OutlinedButton(onClick = { mostrarTimePicker = true }) {
                    Text("Hora: $horaTexto")
                }
            }

            if (mostrarDatePicker) {
                val estadoFecha = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = { mostrarDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            estadoFecha.selectedDateMillis?.let {
                                fechaTexto = SimpleDateFormat("dd/MM/yyyy", Locale("es")).format(Date(it))
                            }
                            mostrarDatePicker = false
                        }) { Text("Aceptar") }
                    },
                    dismissButton = {
                        TextButton(onClick = { mostrarDatePicker = false }) { Text("Cancelar") }
                    }
                ) {
                    DatePicker(state = estadoFecha)
                }
            }

            if (mostrarTimePicker) {
                val estadoHora = rememberTimePickerState()
                AlertDialog(
                    onDismissRequest = { mostrarTimePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            horaTexto = String.format("%02d:%02d", estadoHora.hour, estadoHora.minute)
                            mostrarTimePicker = false
                        }) { Text("Aceptar") }
                    },
                    dismissButton = {
                        TextButton(onClick = { mostrarTimePicker = false }) { Text("Cancelar") }
                    },
                    text = { TimePicker(state = estadoHora) }
                )
            }
        }

        // 7. Chips de filtro
        ElementoDemo(
            nombre = "Chips de filtro seleccionables",
            explicacion = "Pequeñas etiquetas que se pueden activar o desactivar para filtrar contenido, mostrando visualmente cuáles están activas."
        ) {
            val categorias = listOf("Ropa", "Electrónica", "Hogar", "Deportes")
            var seleccionadas by remember { mutableStateOf(setOf<String>()) }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                categorias.forEach { categoria ->
                    FilterChip(
                        selected = categoria in seleccionadas,
                        onClick = {
                            seleccionadas = if (categoria in seleccionadas) {
                                seleccionadas - categoria
                            } else {
                                seleccionadas + categoria
                            }
                        },
                        label = { Text(categoria) }
                    )
                }
            }
        }
    }
}