# Catálogo de Elementos UI — Views y XML

Implementación del catálogo interactivo de elementos de interfaz usando **Views y XML** tradicionales de Android (Kotlin).

## Tecnología

- **Lenguaje:** Kotlin
- **UI Toolkit:** Views + XML (Material Components)
- **Navegación:** Navigation Component (`NavHostFragment`) + `DrawerLayout` como menú lateral
- **Carga de imágenes:** Glide
- **Acceso a vistas:** View Binding (sin `findViewById`)

## Cómo compilar y ejecutar

1. Abre Android Studio.
2. `File > Open` y selecciona esta carpeta (`android-views/`).
3. Espera a que Gradle sincronice las dependencias.
4. Conecta un dispositivo Android o inicia un emulador.
5. Presiona **Run** (o `Shift+F10`).

Para generar el APK manualmente:
```
./gradlew assembleDebug
```
El archivo se genera en `app/build/outputs/apk/debug/app-debug.apk`.

## Estructura de la app

La app tiene una pantalla principal con acceso a 6 secciones, navegables mediante un menú lateral (`NavigationView` dentro de un `DrawerLayout`) disponible en todas las pantallas:

1. **Entrada de Texto** — campo simple, con validación en tiempo real, contraseña con mostrar/ocultar (`TextInputLayout`), teclados especiales (numérico, correo, teléfono), multilínea, sugerencias automáticas (`AutoCompleteTextView`), y barra de búsqueda (`SearchView`).
2. **Botones y Acciones** — botón relleno/contorno/texto, con ícono (solo ícono e ícono+texto), FAB normal/extendido, selector segmentado (`MaterialButtonToggleGroup`), deshabilitado y en estado de carga.
3. **Elementos de Selección** — checkbox (con estado indeterminado simulado), radio buttons, switch (`MaterialSwitch`), slider de valor único y de rango (`Slider`/`RangeSlider`), dropdown (`ExposedDropdownMenu`), selector de fecha y de hora (`DatePickerDialog`/`MaterialTimePicker`), chips de filtro.
4. **Listas y Colecciones** — lista vertical de 15+ elementos (`RecyclerView`), cuadrícula (`GridLayoutManager`), lista con encabezados de sección (dos tipos de elemento), detalle al tocar un elemento, swipe para eliminar (`ItemTouchHelper`), pull-to-refresh (`SwipeRefreshLayout`), estado vacío, y pestañas deslizables (`TabLayout`/`ViewPager2`).
5. **Información y Retroalimentación** — estilos de texto, imagen local y desde URL, progreso lineal y circular (determinado/indeterminado), toast/snackbar (simple y con acción), diálogo de confirmación (`AlertDialog`), bottom sheet (`BottomSheetDialog`), tarjeta/separador/badge.
6. **Contenedores y Estructura** — fila/columna/superposición (`LinearLayout`/`FrameLayout`), contenedor con scroll vertical, barra superior de ejemplo (`MaterialToolbar`), demo de navegación inferior (`BottomNavigationView`), y distribución con pesos proporcionales (`layout_weight`).

### Conexión entre secciones

Un texto capturado en el campo simple de la **Sección 1** se agrega, mediante el botón "Agregar a favoritos", a la lista vertical de la **Sección 4**, usando un `SharedViewModel` compartido a nivel de Activity (`data/SharedViewModel.kt`) como fuente de datos: la Sección 1 escribe en su `LiveData<List<String>>` y la Sección 4 lo observa con `observe(viewLifecycleOwner)`, actualizándose automáticamente y resaltando esos elementos con ⭐ al inicio de la lista.

## Capturas de pantalla

| Sección | Captura |
|---|---|
| Pantalla principal | ![Inicio](../docs/iniciox.png) |
| Entrada de Texto | ![Entrada de Texto](../docs/entrada-textox.png) |
| Botones y Acciones | ![Botones](../docs/botonesx.png) |
| Elementos de Selección | ![Selección](../docs/seleccionx.png) |
| Listas y Colecciones | ![Listas](../docs/listasx.png) |
| Información y Retroalimentación | ![Info](../docs/info-feedbackx.png) |
| Contenedores y Estructura | ![Contenedores](../docs/contenedoresx.png) |