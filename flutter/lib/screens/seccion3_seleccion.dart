import 'package:flutter/material.dart';
import '../widgets/app_drawer.dart';

class Seccion3Seleccion extends StatefulWidget {
  const Seccion3Seleccion({super.key});

  @override
  State<Seccion3Seleccion> createState() => _Seccion3SeleccionState();
}

class _Seccion3SeleccionState extends State<Seccion3Seleccion> {
  bool? _checkboxSimple = false;
  bool? _checkboxIndeterminado;

  String _opcionRadio = 'A';

  bool _switchValor = false;

  double _sliderValor = 50;
  RangeValues _rangoValores = const RangeValues(20, 80);

  String? _dropdownValor = 'Opción 1';

  DateTime? _fechaSeleccionada;
  TimeOfDay? _horaSeleccionada;

  final Set<String> _chipsSeleccionados = {};
  final List<String> _chipsDisponibles = ['Nuevo', 'Popular', 'Oferta', 'Recomendado'];

  Future<void> _elegirFecha() async {
    final fecha = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2020),
      lastDate: DateTime(2030),
    );
    if (fecha != null) setState(() => _fechaSeleccionada = fecha);
  }

  Future<void> _elegirHora() async {
    final hora = await showTimePicker(
      context: context,
      initialTime: TimeOfDay.now(),
    );
    if (hora != null) setState(() => _horaSeleccionada = hora);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Elementos de selección')),
      drawer: const AppDrawer(currentIndex: 2),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _ElementoDemo(
            titulo: 'Casilla de verificación',
            explicacion: 'Permite activar o desactivar una opción de forma independiente.',
            demo: CheckboxListTile(
              title: const Text('Acepto los términos'),
              value: _checkboxSimple,
              onChanged: (valor) => setState(() => _checkboxSimple = valor),
              controlAffinity: ListTileControlAffinity.leading,
            ),
          ),
          _ElementoDemo(
            titulo: 'Casilla con estado indeterminado',
            explicacion: 'Además de marcado y desmarcado, puede mostrar un estado intermedio, útil cuando representa la selección parcial de un grupo.',
            demo: CheckboxListTile(
              title: const Text('Seleccionar todo (parcial)'),
              tristate: true,
              value: _checkboxIndeterminado,
              onChanged: (valor) => setState(() => _checkboxIndeterminado = valor),
              controlAffinity: ListTileControlAffinity.leading,
            ),
          ),
          _ElementoDemo(
            titulo: 'Botones de opción (radio)',
            explicacion: 'Grupo de opciones mutuamente excluyentes: solo una puede estar activa a la vez.',
            demo: Column(
              children: ['A', 'B', 'C'].map((opcion) {
                return RadioListTile<String>(
                  title: Text('Opción $opcion'),
                  value: opcion,
                  groupValue: _opcionRadio,
                  onChanged: (valor) => setState(() => _opcionRadio = valor!),
                );
              }).toList(),
            ),
          ),
          _ElementoDemo(
            titulo: 'Interruptor (switch)',
            explicacion: 'Activa o desactiva una opción con un solo toque, típico para ajustes.',
            demo: SwitchListTile(
              title: const Text('Notificaciones'),
              value: _switchValor,
              onChanged: (valor) => setState(() => _switchValor = valor),
            ),
          ),
          _ElementoDemo(
            titulo: 'Deslizador de valor único',
            explicacion: 'Permite elegir un valor dentro de un rango deslizando el dedo.',
            demo: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Volumen: ${_sliderValor.round()}'),
                Slider(
                  value: _sliderValor,
                  min: 0,
                  max: 100,
                  divisions: 20,
                  label: _sliderValor.round().toString(),
                  onChanged: (valor) => setState(() => _sliderValor = valor),
                ),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Deslizador de rango',
            explicacion: 'Permite elegir un rango entre dos valores mínimo y máximo.',
            demo: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Precio: \$${_rangoValores.start.round()} - \$${_rangoValores.end.round()}'),
                RangeSlider(
                  values: _rangoValores,
                  min: 0,
                  max: 100,
                  divisions: 20,
                  labels: RangeLabels(
                    _rangoValores.start.round().toString(),
                    _rangoValores.end.round().toString(),
                  ),
                  onChanged: (valores) => setState(() => _rangoValores = valores),
                ),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Lista desplegable',
            explicacion: 'Muestra un menú con varias opciones de las que solo se puede elegir una.',
            demo: DropdownButtonFormField<String>(
              initialValue: _dropdownValor,
              decoration: const InputDecoration(border: OutlineInputBorder()),
              items: ['Opción 1', 'Opción 2', 'Opción 3']
                  .map((op) => DropdownMenuItem(value: op, child: Text(op)))
                  .toList(),
              onChanged: (valor) => setState(() => _dropdownValor = valor),
            ),
          ),
          _ElementoDemo(
            titulo: 'Selector de fecha',
            explicacion: 'Abre un calendario nativo para elegir una fecha específica.',
            demo: Row(
              children: [
                ElevatedButton.icon(
                  onPressed: _elegirFecha,
                  icon: const Icon(Icons.calendar_today),
                  label: const Text('Elegir fecha'),
                ),
                const SizedBox(width: 12),
                Text(
                  _fechaSeleccionada == null
                      ? 'Sin seleccionar'
                      : '${_fechaSeleccionada!.day}/${_fechaSeleccionada!.month}/${_fechaSeleccionada!.year}',
                ),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Selector de hora',
            explicacion: 'Abre un reloj nativo para elegir una hora específica.',
            demo: Row(
              children: [
                ElevatedButton.icon(
                  onPressed: _elegirHora,
                  icon: const Icon(Icons.access_time),
                  label: const Text('Elegir hora'),
                ),
                const SizedBox(width: 12),
                Text(_horaSeleccionada == null ? 'Sin seleccionar' : _horaSeleccionada!.format(context)),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Chips de filtro',
            explicacion: 'Etiquetas seleccionables que se pueden activar simultáneamente, comunes en filtros de búsqueda.',
            demo: Wrap(
              spacing: 8,
              children: _chipsDisponibles.map((chip) {
                final seleccionado = _chipsSeleccionados.contains(chip);
                return FilterChip(
                  label: Text(chip),
                  selected: seleccionado,
                  onSelected: (valor) {
                    setState(() {
                      if (valor) {
                        _chipsSeleccionados.add(chip);
                      } else {
                        _chipsSeleccionados.remove(chip);
                      }
                    });
                  },
                );
              }).toList(),
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