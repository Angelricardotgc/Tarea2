package com.gmail.atellezgironcastro.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.gmail.atellezgironcastro.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoFeedbackScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sección 5: Información y Retroalimentación", style = MaterialTheme.typography.headlineSmall)

        // 1. Textos con distintos estilos
        ElementoDemo(
            nombre = "Textos con distintos estilos, tamaños y énfasis",
            explicacion = "Material Design define una escala tipográfica: títulos grandes, cuerpo de texto normal, etiquetas pequeñas, cada uno con su propio peso e importancia visual."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Título grande", style = MaterialTheme.typography.headlineLarge)
                Text("Título mediano", style = MaterialTheme.typography.titleMedium)
                Text("Texto de cuerpo normal", style = MaterialTheme.typography.bodyMedium)
                Text("Texto en negrita", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
                Text("Texto en cursiva", style = MaterialTheme.typography.bodyMedium.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic))
                Text("Etiqueta pequeña", style = MaterialTheme.typography.labelSmall)
            }
        }

        // 2. Imagen local e imagen desde URL, con distintos modos de escalado
        ElementoDemo(
            nombre = "Imagen local e imagen desde URL",
            explicacion = "Una imagen puede venir empaquetada dentro de la app (local) o cargarse desde Internet (URL). El modo de escalado controla cómo se ajusta al espacio disponible."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Imagen local (Crop):", style = MaterialTheme.typography.labelMedium)
                androidx.compose.foundation.Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Ícono local de la app",
                    modifier = Modifier
                        .size(120.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentScale = ContentScale.Crop
                )
                Text("Imagen desde URL (Fit):", style = MaterialTheme.typography.labelMedium)
                AsyncImage(
                    model = "https://picsum.photos/300/200",
                    contentDescription = "Imagen de ejemplo desde Internet",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentScale = ContentScale.Fit
                )
            }
        }

        // 3. Indicador de progreso lineal y circular, determinado e indeterminado
        ElementoDemo(
            nombre = "Indicador de progreso (lineal y circular)",
            explicacion = "El modo determinado muestra un avance específico (por ejemplo 60%). El modo indeterminado solo indica que algo está cargando, sin saber cuánto falta."
        ) {
            var progreso by remember { mutableStateOf(0.6f) }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Lineal determinado (${(progreso * 100).toInt()}%):", style = MaterialTheme.typography.labelMedium)
                LinearProgressIndicator(progress = { progreso }, modifier = Modifier.fillMaxWidth())
                Text("Lineal indeterminado:", style = MaterialTheme.typography.labelMedium)
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Circular determinado", style = MaterialTheme.typography.labelSmall)
                        CircularProgressIndicator(progress = { progreso })
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Circular indeterminado", style = MaterialTheme.typography.labelSmall)
                        CircularProgressIndicator()
                    }
                }
            }
        }

        // 4. Toast y Snackbar
        ElementoDemo(
            nombre = "Mensaje emergente breve (Toast) y con acción (Snackbar)",
            explicacion = "El Toast es un mensaje breve que desaparece solo, sin acciones. El Snackbar aparece en la parte inferior y puede incluir un botón de acción, como 'Deshacer'."
        ) {
            val contexto = LocalContext.current
            val estadoSnackbar = remember { SnackbarHostState() }
            val alcance = rememberCoroutineScope()

            Box(modifier = Modifier.fillMaxWidth().height(80.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        Toast.makeText(contexto, "Este es un mensaje Toast", Toast.LENGTH_SHORT).show()
                    }) { Text("Mostrar Toast") }

                    Button(onClick = {
                        alcance.launch {
                            val resultado = estadoSnackbar.showSnackbar(
                                message = "Elemento eliminado",
                                actionLabel = "Deshacer"
                            )
                            if (resultado == SnackbarResult.ActionPerformed) {
                                Toast.makeText(contexto, "Acción deshecha", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }) { Text("Mostrar Snackbar") }
                }
                SnackbarHost(hostState = estadoSnackbar, modifier = Modifier.align(Alignment.BottomCenter))
            }
        }

        // 5. Diálogo de confirmación
        ElementoDemo(
            nombre = "Diálogo de confirmación",
            explicacion = "Interrumpe al usuario para confirmar una acción importante o irreversible antes de ejecutarla."
        ) {
            var mostrarDialogo by remember { mutableStateOf(false) }
            var resultadoTexto by remember { mutableStateOf("Sin confirmar") }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Button(onClick = { mostrarDialogo = true }) { Text("Eliminar cuenta") }
                Text("Resultado: $resultadoTexto", style = MaterialTheme.typography.bodySmall)
            }

            if (mostrarDialogo) {
                AlertDialog(
                    onDismissRequest = { mostrarDialogo = false },
                    title = { Text("¿Eliminar cuenta?") },
                    text = { Text("Esta acción no se puede deshacer. ¿Deseas continuar?") },
                    confirmButton = {
                        TextButton(onClick = {
                            resultadoTexto = "Confirmado"
                            mostrarDialogo = false
                        }) { Text("Eliminar") }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            resultadoTexto = "Cancelado"
                            mostrarDialogo = false
                        }) { Text("Cancelar") }
                    }
                )
            }
        }

        // 6. Hoja inferior (Bottom Sheet)
        ElementoDemo(
            nombre = "Hoja inferior (Bottom Sheet)",
            explicacion = "Panel que se desliza desde abajo, usado para mostrar opciones o detalles adicionales sin cambiar de pantalla."
        ) {
            var mostrarHoja by remember { mutableStateOf(false) }
            val estadoHoja = rememberModalBottomSheetState()

            Button(onClick = { mostrarHoja = true }) { Text("Abrir hoja inferior") }

            if (mostrarHoja) {
                ModalBottomSheet(
                    onDismissRequest = { mostrarHoja = false },
                    sheetState = estadoHoja
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text("Opciones", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Compartir")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Editar")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Eliminar")
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        // 7. Tarjeta, separador y distintivo numérico (badge)
        ElementoDemo(
            nombre = "Tarjeta, separador y distintivo numérico (badge)",
            explicacion = "La tarjeta agrupa contenido relacionado con elevación visual. El separador divide secciones. El badge muestra un contador, típico en notificaciones."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text("Esto es una tarjeta (Card)", modifier = Modifier.padding(16.dp))
                }
                HorizontalDivider()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BadgedBox(badge = { Badge { Text("5") } }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notificaciones")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Tienes 5 notificaciones nuevas")
                }
            }
        }
    }
}