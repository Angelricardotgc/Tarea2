package com.gmail.atellezgironcastro.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items as itemsLista
import androidx.compose.foundation.lazy.grid.items as itemsCuadricula
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.atellezgironcastro.datos.ElementoLista
import com.gmail.atellezgironcastro.datos.RepositorioListas
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ListasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sección 4: Listas y Colecciones", style = MaterialTheme.typography.headlineSmall)

        // 1. Lista vertical con encabezados, detalle, swipe para eliminar, pull-to-refresh y estado vacío
        ElementoDemo(
            nombre = "Lista vertical (encabezados, detalle, swipe y pull-to-refresh)",
            explicacion = "Lista con más de 15 elementos agrupados por encabezado. Toca uno para ver su detalle, deslízalo hacia la izquierda para eliminarlo, o desliza hacia abajo desde arriba para actualizar. Los elementos 'Capturado' vienen de la Sección 1."
        ) {
            var actualizando by remember { mutableStateOf(false) }
            val alcance = rememberCoroutineScope()
            var elementoSeleccionado by remember { mutableStateOf<ElementoLista?>(null) }

            val agrupados = RepositorioListas.elementos.groupBy { it.categoria }

            PullToRefreshBox(
                isRefreshing = actualizando,
                onRefresh = {
                    alcance.launch {
                        actualizando = true
                        delay(1200)
                        actualizando = false
                    }
                },
                modifier = Modifier.height(400.dp)
            ) {
                if (RepositorioListas.elementos.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Filled.Inbox,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.outline
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("No hay elementos en la lista", color = MaterialTheme.colorScheme.outline)
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        agrupados.forEach { (categoria, itemsDeCategoria) ->
                            item {
                                Text(
                                    text = categoria,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                            itemsLista(itemsDeCategoria, key = { it.id }) { elemento ->
                                val estadoSwipe = rememberSwipeToDismissBoxState(
                                    confirmValueChange = {
                                        if (it == SwipeToDismissBoxValue.EndToStart) {
                                            RepositorioListas.eliminar(elemento)
                                            true
                                        } else false
                                    }
                                )
                                SwipeToDismissBox(
                                    state = estadoSwipe,
                                    backgroundContent = {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(MaterialTheme.colorScheme.errorContainer)
                                                .padding(horizontal = 16.dp),
                                            horizontalArrangement = Arrangement.End,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
                                        }
                                    }
                                ) {
                                    Column {
                                        ListItem(
                                            headlineContent = { Text(elemento.titulo) },
                                            supportingContent = { Text("Toca para ver detalle") },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { elementoSeleccionado = elemento }
                                        )
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }
            }

            elementoSeleccionado?.let { elemento ->
                AlertDialog(
                    onDismissRequest = { elementoSeleccionado = null },
                    confirmButton = {
                        TextButton(onClick = { elementoSeleccionado = null }) { Text("Cerrar") }
                    },
                    title = { Text(elemento.titulo) },
                    text = { Text("ID: ${elemento.id}\nCategoría: ${elemento.categoria}") }
                )
            }
        }

        // 2. Cuadrícula de elementos
        ElementoDemo(
            nombre = "Cuadrícula de elementos",
            explicacion = "Organiza elementos en una rejilla de columnas, útil para mostrar imágenes o tarjetas de forma compacta."
        ) {
            val elementosGrid = (1..12).map { "Ítem $it" }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.height(220.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsCuadricula(elementosGrid) { texto ->
                    Card(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(texto, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }

        // 3. Pestañas con contenido deslizable
        ElementoDemo(
            nombre = "Pestañas con contenido deslizable",
            explicacion = "Permite cambiar de contenido tocando una pestaña o deslizando el dedo horizontalmente sobre el contenido."
        ) {
            val titulosPestanas = listOf("Popular", "Reciente", "Favoritos")
            val estadoPager = rememberPagerState(pageCount = { titulosPestanas.size })
            val alcance = rememberCoroutineScope()

            Column {
                TabRow(selectedTabIndex = estadoPager.currentPage) {
                    titulosPestanas.forEachIndexed { indice, titulo ->
                        Tab(
                            selected = estadoPager.currentPage == indice,
                            onClick = { alcance.launch { estadoPager.animateScrollToPage(indice) } },
                            text = { Text(titulo) }
                        )
                    }
                }
                HorizontalPager(state = estadoPager, modifier = Modifier.height(120.dp)) { pagina ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Contenido de la pestaña: ${titulosPestanas[pagina]}")
                    }
                }
            }
        }
    }
}