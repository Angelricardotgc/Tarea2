import 'package:flutter/material.dart';
import '../widgets/app_drawer.dart';

class Seccion2Botones extends StatefulWidget {
  const Seccion2Botones({super.key});

  @override
  State<Seccion2Botones> createState() => _Seccion2BotonesState();
}

class _Seccion2BotonesState extends State<Seccion2Botones> {
  bool _cargando = false;
  bool _favorito = false;
  int _segmentoSeleccionado = 0;
  final List<bool> _toggleSeleccionado = [true, false, false];

  void _mostrarRespuesta(String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(mensaje), duration: const Duration(seconds: 1)),
    );
  }

  Future<void> _simularCarga() async {
    setState(() => _cargando = true);
    await Future.delayed(const Duration(seconds: 2));
    if (mounted) setState(() => _cargando = false);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Botones y acciones')),
      drawer: const AppDrawer(currentIndex: 1),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _ElementoDemo(
            titulo: 'Botón relleno',
            explicacion: 'Botón sólido usado para la acción principal de una pantalla.',
            demo: ElevatedButton(
              onPressed: () => _mostrarRespuesta('Botón relleno presionado'),
              child: const Text('Confirmar'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Botón con contorno',
            explicacion: 'Botón con borde y fondo transparente, usado para acciones secundarias.',
            demo: OutlinedButton(
              onPressed: () => _mostrarRespuesta('Botón con contorno presionado'),
              child: const Text('Cancelar'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Botón de solo texto',
            explicacion: 'Botón sin fondo ni borde, para acciones de menor jerarquía visual.',
            demo: TextButton(
              onPressed: () => _mostrarRespuesta('Botón de texto presionado'),
              child: const Text('Omitir'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Botón de solo ícono',
            explicacion: 'Botón compacto que usa solo un ícono para representar la acción.',
            demo: IconButton(
              onPressed: () => setState(() => _favorito = !_favorito),
              icon: Icon(_favorito ? Icons.favorite : Icons.favorite_border),
              color: Colors.red,
            ),
          ),
          _ElementoDemo(
            titulo: 'Botón con ícono y texto',
            explicacion: 'Combina un ícono con una etiqueta de texto para mayor claridad.',
            demo: ElevatedButton.icon(
              onPressed: () => _mostrarRespuesta('Descargando...'),
              icon: const Icon(Icons.download),
              label: const Text('Descargar'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Botón de acción flotante (FAB)',
            explicacion: 'Botón circular flotante para la acción más importante de la pantalla.',
            demo: FloatingActionButton(
              heroTag: 'fab_normal',
              onPressed: () => _mostrarRespuesta('FAB presionado'),
              child: const Icon(Icons.add),
            ),
          ),
          _ElementoDemo(
            titulo: 'FAB extendido',
            explicacion: 'Versión alargada del FAB que incluye un texto descriptivo junto al ícono.',
            demo: FloatingActionButton.extended(
              heroTag: 'fab_extendido',
              onPressed: () => _mostrarRespuesta('FAB extendido presionado'),
              icon: const Icon(Icons.edit),
              label: const Text('Editar'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Botón de alternancia (toggle)',
            explicacion: 'Grupo de botones donde se puede activar o desactivar cada opción de forma independiente.',
            demo: ToggleButtons(
              isSelected: _toggleSeleccionado,
              onPressed: (index) {
                setState(() => _toggleSeleccionado[index] = !_toggleSeleccionado[index]);
              },
              children: const [
                Padding(padding: EdgeInsets.symmetric(horizontal: 12), child: Icon(Icons.format_bold)),
                Padding(padding: EdgeInsets.symmetric(horizontal: 12), child: Icon(Icons.format_italic)),
                Padding(padding: EdgeInsets.symmetric(horizontal: 12), child: Icon(Icons.format_underline)),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Selector segmentado',
            explicacion: 'Permite elegir una sola opción entre varias, mostradas como segmentos unidos.',
            demo: SegmentedButton<int>(
              segments: const [
                ButtonSegment(value: 0, label: Text('Día')),
                ButtonSegment(value: 1, label: Text('Semana')),
                ButtonSegment(value: 2, label: Text('Mes')),
              ],
              selected: {_segmentoSeleccionado},
              onSelectionChanged: (nuevaSeleccion) {
                setState(() => _segmentoSeleccionado = nuevaSeleccion.first);
              },
            ),
          ),
          _ElementoDemo(
            titulo: 'Botón deshabilitado',
            explicacion: 'Botón inactivo que no responde a toques, usado cuando una acción no está disponible.',
            demo: const ElevatedButton(
              onPressed: null,
              child: Text('No disponible'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Botón en estado de carga',
            explicacion: 'Muestra un indicador de progreso mientras se completa una acción, y luego vuelve a su estado normal.',
            demo: ElevatedButton(
              onPressed: _cargando ? null : _simularCarga,
              child: _cargando
                  ? const SizedBox(
                      width: 18,
                      height: 18,
                      child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white),
                    )
                  : const Text('Guardar'),
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