# android-views — Catálogo de UI (Views + XML)

Versión nativa de Android construida con **Kotlin** y **layouts XML** (sistema de Views clásico), sin Jetpack Compose.

## Cómo compilar y ejecutar

1. Abre la carpeta `android-views/` en Android Studio (Koala o más reciente) como un proyecto existente.
2. Espera a que Gradle sincronice las dependencias (requiere conexión a internet la primera vez).
3. Conecta un dispositivo físico o inicia un emulador con **API 24 o superior**.
4. Ejecuta la configuración `app` (▶ Run).

Para generar el APK manualmente:

```bash
./gradlew assembleDebug
```

El APK se genera en `app/build/outputs/apk/debug/app-debug.apk`.

## Arquitectura

- **1 Activity** (`MainActivity`) que aloja un `DrawerLayout` con menú lateral.
- **Jetpack Navigation Component** controla la navegación entre la pantalla principal y las 6 secciones, cada una implementada como un `Fragment` independiente.
- **View Binding** en todos los layouts (sin `findViewById`).
- **`SharedViewModel`** (a nivel de Activity) implementa la conexión entre secciones: un nombre capturado en la Sección 1 aparece automáticamente como elemento destacado al inicio de la lista vertical de la Sección 4.

## Estructura de carpetas relevante

```
app/src/main/java/mx/edu/ipn/uicatalog/
 ├─ MainActivity.kt
 ├─ data/SharedViewModel.kt
 └─ ui/
     ├─ home/HomeFragment.kt
     ├─ section1/Section1Fragment.kt      Entrada de texto
     ├─ section2/Section2Fragment.kt      Botones y acciones
     ├─ section3/Section3Fragment.kt      Elementos de selección
     ├─ section4/Section4Fragment.kt + adapters   Listas y colecciones
     ├─ section5/Section5Fragment.kt      Información y retroalimentación
     └─ section6/Section6Fragment.kt      Contenedores y estructura
```

## Notas

- El tema (`Theme.UICatalog`) hereda de `Theme.Material3.DayNight`, por lo que se adapta automáticamente al modo claro/oscuro del sistema (ver `res/values/themes.xml` y `res/values-night/themes.xml`).
- Todos los textos de interfaz y documentación están en español.
- La imagen cargada por URL en la Sección 5 usa Glide y requiere conexión a internet en el dispositivo.
