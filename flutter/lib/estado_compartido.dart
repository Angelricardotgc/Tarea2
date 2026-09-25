import 'package:flutter/material.dart';

/// Estado compartido entre secciones.
/// Los nombres capturados en la Sección 1 (Entrada de texto) se agregan
/// aquí y aparecen automáticamente en la lista vertical de la Sección 4.
final ValueNotifier<List<String>> nombresCapturados = ValueNotifier<List<String>>([]);