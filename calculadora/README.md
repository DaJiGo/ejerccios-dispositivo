# Calculadora básica (Android Studio - Kotlin)

Calculadora con suma, resta, multiplicación, división, porcentaje,
cambio de signo (±), botón de borrar (⌫) y limpiar (C).

## Archivos
- `activity_main.xml` → reemplaza tu `res/layout/activity_main.xml`
- `MainActivity.kt` → reemplaza tu `MainActivity.kt`
- `estilos_agregar_a_themes_xml.xml` → NO reemplaza nada, contiene 4 estilos
  que debes copiar dentro de tu `res/values/themes.xml`

## Pasos

1. Crea un proyecto nuevo: **New Project → Empty Views Activity → Kotlin**
2. Reemplaza `activity_main.xml` y `MainActivity.kt` con los de esta carpeta.
3. Ajusta el `package` en la primera línea de `MainActivity.kt` para que
   coincida con el de tu proyecto.
4. Abre `res/values/themes.xml` y, dentro del bloque `<resources> ... </resources>`,
   pega los 4 estilos (`BotonBase`, `BotonNumero`, `BotonFuncion`, `BotonOperador`,
   `BotonIgual`) del archivo `estilos_agregar_a_themes_xml.xml`.
   Debe quedar tu tema principal + estos 4 estilos, todos dentro del mismo
   `<resources>`.
5. Sync Gradle y Run.

## Cómo funciona
- Todos los botones comparten un mismo `OnClickListener` que decide qué hacer
  según el `tag` del botón (número, operador, o función especial).
- Soporta operaciones encadenadas (ej. `5 + 3 + 2 =`).
- Si divides entre 0, muestra "Error" y reinicia el número actual.
