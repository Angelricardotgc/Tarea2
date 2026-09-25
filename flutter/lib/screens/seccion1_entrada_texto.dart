import 'package:flutter/material.dart';
import '../estado_compartido.dart';
import '../widgets/app_drawer.dart';

class Seccion1EntradaTexto extends StatefulWidget {
  const Seccion1EntradaTexto({super.key});

  @override
  State<Seccion1EntradaTexto> createState() => _Seccion1EntradaTextoState();
}

class _Seccion1EntradaTextoState extends State<Seccion1EntradaTexto> {
  bool _mostrarPassword = false;
  final _formKey = GlobalKey<FormState>();
  final _nombreController = TextEditingController();

  static const List<String> _frutas = ['Manzana', 'Mango', 'Melón', 'Naranja', 'Pera'];

  List<String> _resultadosBusqueda = [];
  bool _busquedaActiva = false;

  void _buscarFruta(String texto) {
    setState(() {
      _busquedaActiva = texto.isNotEmpty;
      if (texto.isEmpty) {
        _resultadosBusqueda = [];
      } else {
        _resultadosBusqueda = _frutas
            .where((f) => f.toLowerCase().contains(texto.toLowerCase()))
            .toList();
      }
    });
  }

  void _agregarNombre() {
    final texto = _nombreController.text.trim();
    if (texto.isEmpty) return;
    nombresCapturados.value = [...nombresCapturados.value, texto];
    _nombreController.clear();
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text('"$texto" agregado a la lista de la Sección 4')),
    );
  }

  @override
  void dispose() {
    _nombreController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Entrada de texto')),
      drawer: const AppDrawer(currentIndex: 0),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _ElementoDemo(
            titulo: 'Campo de texto simple',
            explicacion: 'Campo básico con etiqueta para capturar texto libre. Escribe un nombre '
                'y presiona "Agregar": aparecerá en la lista vertical de la Sección 4 '
                '(conexión entre secciones).',
            demo: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TextField(
                  controller: _nombreController,
                  decoration: const InputDecoration(
                    labelText: 'Nombre',
                    border: OutlineInputBorder(),
                  ),
                ),
                const SizedBox(height: 8),
                ElevatedButton.icon(
                  onPressed: _agregarNombre,
                  icon: const Icon(Icons.arrow_forward),
                  label: const Text('Agregar a la Sección 4'),
                ),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Campo con validación',
            explicacion: 'Muestra un mensaje de error visible si el texto ingresado no cumple una regla.',
            demo: Form(
              key: _formKey,
              child: TextFormField(
                decoration: const InputDecoration(
                  labelText: 'Correo',
                  border: OutlineInputBorder(),
                ),
                validator: (value) {
                  if (value == null || !value.contains('@')) {
                    return 'Ingresa un correo válido';
                  }
                  return null;
                },
                onChanged: (_) => _formKey.currentState?.validate(),
              ),
            ),
          ),
          _ElementoDemo(
            titulo: 'Campo de contraseña',
            explicacion: 'Oculta el texto por seguridad, con opción de mostrarlo u ocultarlo.',
            demo: TextField(
              obscureText: !_mostrarPassword,
              decoration: InputDecoration(
                labelText: 'Contraseña',
                border: const OutlineInputBorder(),
                suffixIcon: IconButton(
                  icon: Icon(_mostrarPassword ? Icons.visibility_off : Icons.visibility),
                  onPressed: () => setState(() => _mostrarPassword = !_mostrarPassword),
                ),
              ),
            ),
          ),
          _ElementoDemo(
            titulo: 'Teclado numérico',
            explicacion: 'Muestra un teclado especializado para facilitar la captura de números.',
            demo: const TextField(
              keyboardType: TextInputType.number,
              decoration: InputDecoration(labelText: 'Edad', border: OutlineInputBorder()),
            ),
          ),
          _ElementoDemo(
            titulo: 'Teclado de correo electrónico',
            explicacion: 'Teclado con el símbolo @ visible para facilitar escribir correos.',
            demo: const TextField(
              keyboardType: TextInputType.emailAddress,
              decoration: InputDecoration(labelText: 'Email', border: OutlineInputBorder()),
            ),
          ),
          _ElementoDemo(
            titulo: 'Teclado de teléfono',
            explicacion: 'Teclado numérico especializado para capturar números telefónicos.',
            demo: const TextField(
              keyboardType: TextInputType.phone,
              decoration: InputDecoration(labelText: 'Teléfono', border: OutlineInputBorder()),
            ),
          ),
          _ElementoDemo(
            titulo: 'Campo multilínea',
            explicacion: 'Permite ingresar varias líneas de texto, útil para comentarios largos.',
            demo: const TextField(
              maxLines: 4,
              decoration: InputDecoration(
                labelText: 'Comentarios',
                border: OutlineInputBorder(),
                alignLabelWithHint: true,
              ),
            ),
          ),
          _ElementoDemo(
            titulo: 'Sugerencias automáticas',
            explicacion: 'Despliega opciones de frutas que coinciden con lo escrito. Prueba '
                'escribiendo: Manzana, Mango, Melón, Naranja o Pera. Comparte la misma lista '
                'de datos que la barra de búsqueda de abajo.',
            demo: Autocomplete<String>(
              optionsBuilder: (textEditingValue) {
                if (textEditingValue.text.isEmpty) return const Iterable<String>.empty();
                return _frutas.where((o) =>
                    o.toLowerCase().contains(textEditingValue.text.toLowerCase()));
              },
            ),
          ),
          _ElementoDemo(
            titulo: 'Barra de búsqueda',
            explicacion: 'Filtra en tiempo real la lista de frutas: Manzana, Mango, Melón, '
                'Naranja y Pera. Usa la misma fuente de datos que el elemento de sugerencias '
                'automáticas de arriba.',
            demo: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                SearchBar(
                  hintText: 'Ej: Mango, Pera, Naranja...',
                  leading: const Icon(Icons.search),
                  onChanged: _buscarFruta,
                ),
                if (_resultadosBusqueda.isNotEmpty) ...[
                  const SizedBox(height: 8),
                  ..._resultadosBusqueda.map(
                    (fruta) => ListTile(
                      dense: true,
                      leading: const Icon(Icons.eco, size: 18),
                      title: Text(fruta),
                    ),
                  ),
                ] else if (_busquedaActiva) ...[
                  const Padding(
                    padding: EdgeInsets.symmetric(vertical: 8),
                    child: Text('Sin resultados', style: TextStyle(fontStyle: FontStyle.italic)),
                  ),
                ],
              ],
            ),
          ),
        ],
      ),
    );
  }
}

/// Widget reutilizable: nombre + explicación + demo interactiva
class _ElementoDemo extends StatelessWidget {
  final String titulo;
  final String explicacion;
  final Widget demo;

  const _ElementoDemo({
    required this.titulo,
    required this.explicacion,
    required this.demo,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.only(bottom: 16),
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(titulo, style: Theme.of(context).textTheme.titleMedium),
            const SizedBox(height: 4),
            Text(explicacion, style: Theme.of(context).textTheme.bodySmall),
            const SizedBox(height: 12),
            demo,
          ],
        ),
      ),
    );
  }
}