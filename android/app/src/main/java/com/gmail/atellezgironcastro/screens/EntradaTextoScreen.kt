package com.gmail.atellezgironcastro.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import com.gmail.atellezgironcastro.datos.RepositorioListas
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun EntradaTextoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sección 1: Entrada de Texto", style = MaterialTheme.typography.headlineSmall)

        // 1. Campo de texto simple (con conexión a la Sección 4)
        ElementoDemo(
            nombre = "Campo de texto simple",
            explicacion = "Permite al usuario escribir texto libre. Este dato, al confirmarse, se agrega a la lista de la Sección 4 (conexión entre secciones)."
        ) {
            var texto by remember { mutableStateOf("") }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = texto,
                    onValueChange = { texto = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        if (texto.isNotBlank()) {
                            RepositorioListas.agregar(texto)
                            texto = ""
                        }
                    },
                    enabled = texto.isNotBlank()
                ) {
                    Text("Agregar a la lista (Sección 4)")
                }
            }
        }

        // 2. Campo con validación
        ElementoDemo(
            nombre = "Campo con validación",
            explicacion = "Verifica que el texto cumpla una regla (aquí, mínimo 3 caracteres) y muestra un mensaje de error visible si no se cumple."
        ) {
            var texto by remember { mutableStateOf("") }
            val esInvalido = texto.isNotEmpty() && texto.length < 3
            Column {
                OutlinedTextField(
                    value = texto,
                    onValueChange = { texto = it },
                    label = { Text("Apodo (mín. 3 letras)") },
                    isError = esInvalido,
                    modifier = Modifier.fillMaxWidth()
                )
                if (esInvalido) {
                    Text(
                        text = "El apodo debe tener al menos 3 caracteres",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        // 3. Campo de contraseña
        ElementoDemo(
            nombre = "Campo de contraseña",
            explicacion = "Oculta el texto ingresado por seguridad. Incluye un ícono para mostrar u ocultar el contenido a voluntad."
        ) {
            var password by remember { mutableStateOf("") }
            var mostrarPassword by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = if (mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { mostrarPassword = !mostrarPassword }) {
                        Icon(
                            imageVector = if (mostrarPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (mostrarPassword) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 4. Teclados especiales: numérico, correo, teléfono
        ElementoDemo(
            nombre = "Campos con distintos tipos de teclado",
            explicacion = "El teclado que aparece cambia según el tipo de dato esperado: números, correo electrónico o teléfono."
        ) {
            var numero by remember { mutableStateOf("") }
            var correo by remember { mutableStateOf("") }
            var telefono by remember { mutableStateOf("") }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = numero,
                    onValueChange = { numero = it },
                    label = { Text("Edad (numérico)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // 5. Campo multilínea
        ElementoDemo(
            nombre = "Campo multilínea",
            explicacion = "Permite escribir varias líneas de texto, útil para comentarios o descripciones largas."
        ) {
            var comentario by remember { mutableStateOf("") }
            OutlinedTextField(
                value = comentario,
                onValueChange = { comentario = it },
                label = { Text("Comentarios") },
                minLines = 4,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 6. Campo con sugerencias / desplegable de opciones
        ElementoDemo(
            nombre = "Campo con sugerencias automáticas",
            explicacion = "Muestra una lista desplegable de opciones a medida que el usuario escribe. Toca una sugerencia para completarla automáticamente."
        ) {
            val opciones = listOf("Manzana", "Mango", "Melón", "Mandarina", "Naranja")
            var texto by remember { mutableStateOf("") }
            val sugerencias = opciones.filter {
                it.contains(texto, ignoreCase = true) && texto.isNotEmpty() && it != texto
            }
            Column {
                OutlinedTextField(
                    value = texto,
                    onValueChange = { texto = it },
                    label = { Text("Escribe una fruta (prueba con 'm')") },
                    modifier = Modifier.fillMaxWidth()
                )
                sugerencias.forEach { sugerencia ->
                    Text(
                        text = sugerencia,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { texto = sugerencia }
                    )
                }
            }
        }

        // 7. Barra de búsqueda
        ElementoDemo(
            nombre = "Barra de búsqueda",
            explicacion = "Campo especializado para búsquedas. Filtra en tiempo real una lista de ejemplo (nombres de países) mientras escribes."
        ) {
            val paises = listOf("México", "Argentina", "España", "Colombia", "Chile", "Perú", "Ecuador")
            var busqueda by remember { mutableStateOf("") }
            val resultados = if (busqueda.isEmpty()) paises else paises.filter {
                it.contains(busqueda, ignoreCase = true)
            }
            Column {
                OutlinedTextField(
                    value = busqueda,
                    onValueChange = { busqueda = it },
                    placeholder = { Text("Buscar país...") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (resultados.isEmpty()) {
                    Text("Sin resultados", style = MaterialTheme.typography.bodySmall)
                } else {
                    resultados.forEach { pais ->
                        Text(pais, modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        }
    }
}