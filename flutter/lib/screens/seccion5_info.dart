import 'package:flutter/material.dart';
import '../widgets/app_drawer.dart';

class Seccion5Info extends StatefulWidget {
  const Seccion5Info({super.key});

  @override
  State<Seccion5Info> createState() => _Seccion5InfoState();
}

class _Seccion5InfoState extends State<Seccion5Info> {
  double _progresoLineal = 0.4;
  bool _progresoIndeterminado = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Información y retroalimentación')),
      drawer: const AppDrawer(currentIndex: 4),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _ElementoDemo(
            titulo: 'Textos con distintos estilos',
            explicacion: 'Flutter permite variar tamaño, peso y énfasis del texto según su jerarquía dentro de la pantalla.',
            demo: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Título grande', style: Theme.of(context).textTheme.headlineSmall),
                Text('Subtítulo', style: Theme.of(context).textTheme.titleMedium),
                Text('Texto normal', style: Theme.of(context).textTheme.bodyMedium),
                Text(
                  'Texto en negritas',
                  style: Theme.of(context).textTheme.bodyMedium?.copyWith(fontWeight: FontWeight.bold),
                ),
                Text(
                  'Texto en cursiva',
                  style: Theme.of(context).textTheme.bodyMedium?.copyWith(fontStyle: FontStyle.italic),
                ),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Imagen local',
            explicacion: 'Imagen incluida dentro de los recursos de la aplicación, no requiere internet.',
            demo: Container(
              height: 120,
              decoration: BoxDecoration(
                color: Theme.of(context).colorScheme.primaryContainer,
                borderRadius: BorderRadius.circular(8),
              ),
              child: Icon(
                Icons.image,
                size: 48,
                color: Theme.of(context).colorScheme.onPrimaryContainer,
              ),
            ),
          ),
          _ElementoDemo(
            titulo: 'Imagen desde URL',
            explicacion: 'Imagen descargada de internet en tiempo real, con recorte tipo "cover" para llenar el espacio.',
            demo: ClipRRect(
              borderRadius: BorderRadius.circular(8),
              child: Image.network(
                'https://picsum.photos/400/200',
                height: 150,
                width: double.infinity,
                fit: BoxFit.cover,
                loadingBuilder: (context, child, progreso) {
                  if (progreso == null) return child;
                  return const SizedBox(
                    height: 150,
                    child: Center(child: CircularProgressIndicator()),
                  );
                },
                errorBuilder: (context, error, stackTrace) => Container(
                  height: 150,
                  color: Colors.grey.shade300,
                  alignment: Alignment.center,
                  child: const Icon(Icons.broken_image, size: 40, color: Colors.grey),
                ),
              ),
            ),
          ),
          _ElementoDemo(
            titulo: 'Progreso lineal',
            explicacion: 'Barra horizontal que indica el avance de una tarea, en modo determinado (con valor) o indeterminado (animación continua).',
            demo: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                LinearProgressIndicator(value: _progresoIndeterminado ? null : _progresoLineal),
                const SizedBox(height: 8),
                Row(
                  children: [
                    Expanded(
                      child: Slider(
                        value: _progresoLineal,
                        onChanged: (v) => setState(() => _progresoLineal = v),
                      ),
                    ),
                    Switch(
                      value: _progresoIndeterminado,
                      onChanged: (v) => setState(() => _progresoIndeterminado = v),
                    ),
                  ],
                ),
                const Text('Activa el switch para modo indeterminado', style: TextStyle(fontSize: 12)),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Progreso circular',
            explicacion: 'Indicador circular de avance, útil cuando el espacio horizontal es limitado.',
            demo: Row(
              children: [
                CircularProgressIndicator(value: _progresoIndeterminado ? null : _progresoLineal),
                const SizedBox(width: 16),
                const Text('Mismo valor que la barra de arriba'),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Mensaje emergente breve (toast)',
            explicacion: 'Notificación corta que aparece y desaparece sola, sin requerir interacción del usuario.',
            demo: ElevatedButton(
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Esto es un mensaje breve'), duration: Duration(seconds: 1)),
                );
              },
              child: const Text('Mostrar toast'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Mensaje con acción (snackbar)',
            explicacion: 'Notificación que incluye un botón de acción, por ejemplo para deshacer algo.',
            demo: ElevatedButton(
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(
                    content: const Text('Elemento eliminado'),
                    action: SnackBarAction(
                      label: 'Deshacer',
                      onPressed: () {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('Acción deshecha'), duration: Duration(seconds: 1)),
                        );
                      },
                    ),
                  ),
                );
              },
              child: const Text('Eliminar elemento'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Diálogo de confirmación',
            explicacion: 'Ventana emergente que interrumpe el flujo para pedir confirmación antes de una acción importante.',
            demo: ElevatedButton(
              onPressed: () {
                showDialog(
                  context: context,
                  builder: (_) => AlertDialog(
                    title: const Text('¿Confirmar acción?'),
                    content: const Text('Esta operación no se puede deshacer.'),
                    actions: [
                      TextButton(onPressed: () => Navigator.pop(context), child: const Text('Cancelar')),
                      FilledButton(
                        onPressed: () {
                          Navigator.pop(context);
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(content: Text('Acción confirmada'), duration: Duration(seconds: 1)),
                          );
                        },
                        child: const Text('Confirmar'),
                      ),
                    ],
                  ),
                );
              },
              child: const Text('Mostrar diálogo'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Hoja inferior (bottom sheet)',
            explicacion: 'Panel que se desliza desde la parte inferior de la pantalla, útil para mostrar opciones adicionales.',
            demo: ElevatedButton(
              onPressed: () {
                showModalBottomSheet(
                  context: context,
                  builder: (_) => Padding(
                    padding: const EdgeInsets.all(24),
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: const [
                        Text('Opciones', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 18)),
                        SizedBox(height: 12),
                        ListTile(leading: Icon(Icons.share), title: Text('Compartir')),
                        ListTile(leading: Icon(Icons.edit), title: Text('Editar')),
                        ListTile(leading: Icon(Icons.delete), title: Text('Eliminar')),
                      ],
                    ),
                  ),
                );
              },
              child: const Text('Abrir hoja inferior'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Tarjeta, separador y distintivo',
            explicacion: 'La tarjeta agrupa contenido relacionado, el separador divide secciones visualmente, y el distintivo (badge) resalta una cantidad o estado.',
            demo: Column(
              children: [
                Card(
                  child: ListTile(
                    leading: Badge(
                      label: const Text('3'),
                      child: const Icon(Icons.notifications),
                    ),
                    title: const Text('Notificaciones'),
                    subtitle: const Text('Tienes 3 mensajes nuevos'),
                  ),
                ),
                const Divider(),
                const Text('El separador de arriba divide visualmente el contenido', style: TextStyle(fontSize: 12)),
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