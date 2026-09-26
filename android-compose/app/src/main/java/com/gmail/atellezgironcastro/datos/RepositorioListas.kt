package com.gmail.atellezgironcastro.datos

import androidx.compose.runtime.mutableStateListOf

data class ElementoLista(
    val id: Int,
    val titulo: String,
    val categoria: String // "Capturado" o "Predeterminado", usado para encabezados de sección
)

/**
 * Repositorio simple en memoria, compartido entre la Sección 1 (Entrada de Texto)
 * y la Sección 4 (Listas y Colecciones). Cumple el requisito de "conexión entre secciones":
 * un texto capturado en la Sección 1 se agrega aquí y aparece en la lista de la Sección 4.
 */
object RepositorioListas {
    private var siguienteId = 1

    val elementos = mutableStateListOf(
        ElementoLista(0, "Elemento predeterminado 1", "Predeterminado"),
        ElementoLista(-1, "Elemento predeterminado 2", "Predeterminado"),
        ElementoLista(-2, "Elemento predeterminado 3", "Predeterminado"),
        ElementoLista(-3, "Elemento predeterminado 4", "Predeterminado"),
        ElementoLista(-4, "Elemento predeterminado 5", "Predeterminado"),
        ElementoLista(-5, "Elemento predeterminado 6", "Predeterminado"),
        ElementoLista(-6, "Elemento predeterminado 7", "Predeterminado"),
        ElementoLista(-7, "Elemento predeterminado 8", "Predeterminado"),
        ElementoLista(-8, "Elemento predeterminado 9", "Predeterminado"),
        ElementoLista(-9, "Elemento predeterminado 10", "Predeterminado"),
        ElementoLista(-10, "Elemento predeterminado 11", "Predeterminado"),
        ElementoLista(-11, "Elemento predeterminado 12", "Predeterminado"),
    )

    fun agregar(titulo: String) {
        elementos.add(0, ElementoLista(siguienteId++, titulo, "Capturado"))
    }

    fun eliminar(elemento: ElementoLista) {
        elementos.remove(elemento)
    }
}