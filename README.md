# Catálogo de Elementos UI

Catálogo interactivo de elementos de interfaz de usuario, implementado en tres tecnologías distintas (Android Views/XML, Jetpack Compose y Flutter), con el objetivo de comparar sus componentes básicos, equivalencias entre plataformas y enfoques de construcción de interfaces.

## Datos de identificación

- **Nombre completo:** Tellez Giron Castro Angel Ricardo 
- **Número de boleta:** 2024630154
- **Grupo:** 7CV4

## Descripción de la aplicación

La aplicación presenta un catálogo de elementos de interfaz organizado en 6 secciones (Entrada de Texto, Botones y Acciones, Elementos de Selección, Listas y Colecciones, Información y Retroalimentación, y Contenedores y Estructura). Cada elemento del catálogo muestra su nombre, una breve explicación de su propósito, y una demostración interactiva real.

La misma aplicación se construyó en tres tecnologías para comparar sus diferencias:

- Android nativo con Views y XML (Kotlin + layouts XML)
- Android nativo con Jetpack Compose (Kotlin + funciones composable)
- Flutter (Dart, ejecutándose al menos en Android)

### Conexión entre secciones

Como requisito de integración, un texto capturado en la **Sección 1 (Entrada de Texto)** se agrega dinámicamente a la lista vertical de la **Sección 4 (Listas y Colecciones)**, mediante un repositorio de datos compartido en memoria.

## Instrucciones de compilación y ejecución

### Jetpack Compose (`android-compose/`)

- [Ver README](android/README.md)

### Android Views / XML (`android-views/`)

- [Ver README](Views/README.md)

### Flutter (`flutter/`)

- [Ver README](flutter/README.md)

## Tabla de equivalencias entre tecnologías

| Elemento | Views / XML | Jetpack Compose | Flutter |
|---|---|---|---|
| Campo de texto simple | EditText / TextInputLayout | OutlinedTextField | TextField |
| Campo con validación | TextInputLayout (setError) | OutlinedTextField (isError) | TextField (errorText) |
| Campo de contraseña | EditText (inputType=textPassword) | OutlinedTextField (visualTransformation) | TextField (obscureText) |
| Campos con teclado especial | EditText (inputType) | OutlinedTextField (keyboardOptions) | TextField (keyboardType) |
| Campo multilínea | EditText (inputType=textMultiLine) | OutlinedTextField (minLines) | TextField (maxLines) |
| Campo con sugerencias | AutoCompleteTextView | Lista filtrada + OutlinedTextField | _(pendiente)_ |
| Barra de búsqueda | SearchView | OutlinedTextField + ícono | _(pendiente)_ |
| Botón relleno | Button (estilo Filled) | Button | ElevatedButton |
| Botón con contorno | Button (estilo Outlined) | OutlinedButton | OutlinedButton |
| Botón de texto | Button (estilo Text) | TextButton | TextButton |
| Botón con ícono | ImageButton / Button con drawable | IconButton / Button con Icon | IconButton |
| FAB normal / extendido | FloatingActionButton / ExtendedFloatingActionButton | FloatingActionButton / ExtendedFloatingActionButton | FloatingActionButton / FloatingActionButton.extended |
| Toggle / segmentado | ToggleButton / MaterialButtonToggleGroup | SegmentedButton | ToggleButtons |
| Botón deshabilitado / carga | Button (enabled=false) + ProgressBar | Button (enabled) + CircularProgressIndicator | ElevatedButton (onPressed=null) |
| Checkbox indeterminado | CheckBox (setButtonDrawable) | TriStateCheckbox | Checkbox (tristate) |
| Radio buttons | RadioGroup / RadioButton | RadioButton | Radio |
| Switch | Switch / SwitchMaterial | Switch | Switch |
| Slider simple / rango | SeekBar / RangeSlider (Material) | Slider / RangeSlider | Slider / RangeSlider |
| Dropdown de selección | Spinner | DropdownMenu | DropdownButton |
| Selector de fecha/hora | DatePickerDialog / TimePickerDialog | DatePicker / TimePicker (DatePickerDialog) | showDatePicker / showTimePicker |
| Chips de filtro | ChipGroup / Chip | FilterChip | FilterChip |
| Lista vertical | RecyclerView | LazyColumn | ListView.builder |
| Cuadrícula | RecyclerView (GridLayoutManager) | LazyVerticalGrid | GridView.builder |
| Encabezados de sección | RecyclerView (varios viewType) | LazyColumn (item por grupo) | ListView con headers manuales |
| Swipe para eliminar | ItemTouchHelper | SwipeToDismissBox | Dismissible |
| Pull to refresh | SwipeRefreshLayout | PullToRefreshBox | RefreshIndicator |
| Estado vacío | Vista personalizada condicional | Composable condicional | Widget condicional |
| Pestañas deslizables | TabLayout + ViewPager2 | TabRow + HorizontalPager | TabBar + TabBarView |
| Textos con estilos | TextView (estilos XML) | Text (MaterialTheme.typography) | Text (TextStyle) |
| Imagen local / URL | ImageView + Glide/Coil | Image / AsyncImage (Coil) | Image.asset / Image.network |
| Progreso lineal / circular | ProgressBar | LinearProgressIndicator / CircularProgressIndicator | LinearProgressIndicator / CircularProgressIndicator |
| Toast / Snackbar | Toast / Snackbar | Toast / SnackbarHost | Fluttertoast (paquete) / SnackBar |
| Diálogo de confirmación | AlertDialog (androidx) | AlertDialog | AlertDialog |
| Bottom sheet | BottomSheetDialogFragment | ModalBottomSheet | showModalBottomSheet |
| Tarjeta / separador / badge | CardView / Divider / BadgeDrawable | Card / HorizontalDivider / BadgedBox | Card / Divider / Badge |
| Row / Column / Box | LinearLayout / FrameLayout | Row / Column / Box | Row / Column / Stack |
| Scroll vertical | ScrollView | Modifier.verticalScroll | SingleChildScrollView |
| Barra superior | Toolbar / MaterialToolbar | TopAppBar | AppBar |
| Navegación inferior / lateral | BottomNavigationView / NavigationView (Drawer) | NavigationBar / NavigationDrawerItem | BottomNavigationBar / Drawer |
| Pesos proporcionales | LinearLayout (layout_weight) | Modifier.weight() | Expanded (flex) |

## Reflexión final

_(completar al terminar las tres versiones)_

- **¿En cuál tecnología resultó más rápido construir la interfaz?**
- **¿Cuál generó código más legible?**
- **¿Qué dificultades encontré en cada una?**
- **¿Con cuál preferiría trabajar y por qué?**

## Referencias

_(agregar en formato APA conforme se consulten fuentes)_

