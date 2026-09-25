package com.gmail.atellezgironcastro.navigation

sealed class Rutas(val ruta: String, val titulo: String) {
    object Inicio : Rutas("inicio", "Inicio")
    object EntradaTexto : Rutas("entrada_texto", "Entrada de Texto")
    object Botones : Rutas("botones", "Botones y Acciones")
    object Seleccion : Rutas("seleccion", "Elementos de Selección")
    object Listas : Rutas("listas", "Listas y Colecciones")
    object InfoFeedback : Rutas("info_feedback", "Información y Retroalimentación")
    object Contenedores : Rutas("contenedores", "Contenedores y Estructura")
}

// Lista de las 6 secciones para la barra inferior (excluye Inicio)
val seccionesNavegacion = listOf(
    Rutas.EntradaTexto,
    Rutas.Botones,
    Rutas.Seleccion,
    Rutas.Listas,
    Rutas.InfoFeedback,
    Rutas.Contenedores
)