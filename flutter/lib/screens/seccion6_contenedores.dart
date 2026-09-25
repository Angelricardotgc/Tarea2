import 'package:flutter/material.dart';
import '../widgets/app_drawer.dart';

class Seccion6Contenedores extends StatelessWidget {
  const Seccion6Contenedores({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Contenedores y estructura')),
      drawer: const AppDrawer(currentIndex: 5),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          _ElementoDemo(
            titulo: 'Distribución en fila (Row)',
            explicacion: 'Organiza sus elementos hijos horizontalmente, uno junto al otro.',
            demo: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                _Caja(color: Colors.indigo, texto: '1'),
                _Caja(color: Colors.teal, texto: '2'),
                _Caja(color: Colors.orange, texto: '3'),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Distribución en columna (Column)',
            explicacion: 'Organiza sus elementos hijos verticalmente, uno debajo del otro.',
            demo: Column(
              children: [
                _Caja(color: Colors.indigo, texto: 'A'),
                const SizedBox(height: 8),
                _Caja(color: Colors.teal, texto: 'B'),
                const SizedBox(height: 8),
                _Caja(color: Colors.orange, texto: 'C'),
              ],
            ),
          ),
          _ElementoDemo(
            titulo: 'Distribución superpuesta (Stack)',
            explicacion: 'Coloca elementos uno encima de otro en el mismo espacio, útil para superponer texto sobre imágenes.',
            demo: SizedBox(
              height: 120,
              child: Stack(
                alignment: Alignment.center,
                children: [
                  Container(
                    width: double.infinity,
                    height: 120,
                    color: Colors.indigo.shade200,
                  ),
                  const Icon(Icons.star, size: 48, color: Colors.white),
                  const Positioned(
                    bottom: 8,
                    right: 8,
                    child: Text('Superpuesto', style: TextStyle(color: Colors.white)),
                  ),
                ],
              ),
            ),
          ),
          _ElementoDemo(
            titulo: 'Contenedor con desplazamiento vertical',
            explicacion: 'Permite que contenido más grande que la pantalla se pueda desplazar dentro de un espacio fijo.',
            demo: Container(
              height: 100,
              decoration: BoxDecoration(border: Border.all(color: Colors.grey)),
              child: SingleChildScrollView(
                child: Column(
                  children: List.generate(
                    10,
                    (i) => Padding(
                      padding: const EdgeInsets.all(8),
                      child: Text('Línea desplazable ${i + 1}'),
                    ),
                  ),
                ),
              ),
            ),
          ),
          _ElementoDemo(
            titulo: 'Barra superior con título y acciones',
            explicacion: 'Encabezado fijo que muestra el título de la pantalla y accesos directos a acciones comunes.',
            demo: Material(
              elevation: 2,
              child: AppBar(
                automaticallyImplyLeading: false,
                title: const Text('Mi Pantalla'),
                actions: [
                  IconButton(icon: const Icon(Icons.search), onPressed: () {}),
                  IconButton(icon: const Icon(Icons.more_vert), onPressed: () {}),
                ],
              ),
            ),
          ),
          _ElementoDemo(
            titulo: 'Navegación inferior o menú lateral',
            explicacion: 'Permite moverse entre las secciones principales de una app. Toca el botón para ver una demo interactiva en pantalla completa. Además, esta misma app usa un menú lateral (Drawer) en todas sus pantallas: ábrelo desde el ícono ☰ de arriba.',
            demo: ElevatedButton.icon(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const _DemoNavegacion()),
                );
              },
              icon: const Icon(Icons.menu),
              label: const Text('Ver demo de navegación'),
            ),
          ),
          _ElementoDemo(
            titulo: 'Distribución con pesos proporcionales',
            explicacion: 'Divide el espacio disponible entre varios elementos según una proporción definida (Expanded/Flexible).',
            demo: SizedBox(
              height: 60,
              child: Row(
                children: [
                  Expanded(flex: 2, child: Container(color: Colors.indigo, alignment: Alignment.center, child: const Text('2', style: TextStyle(color: Colors.white)))),
                  Expanded(flex: 1, child: Container(color: Colors.teal, alignment: Alignment.center, child: const Text('1', style: TextStyle(color: Colors.white)))),
                  Expanded(flex: 1, child: Container(color: Colors.orange, alignment: Alignment.center, child: const Text('1', style: TextStyle(color: Colors.white)))),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class _Caja extends StatelessWidget {
  final Color color;
  final String texto;
  const _Caja({required this.color, required this.texto});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 50,
      height: 50,
      color: color,
      alignment: Alignment.center,
      child: Text(texto, style: const TextStyle(color: Colors.white)),
    );
  }
}

/// Pantalla completa que demuestra BottomNavigationBar funcionando de verdad
class _DemoNavegacion extends StatefulWidget {
  const _DemoNavegacion();

  @override
  State<_DemoNavegacion> createState() => _DemoNavegacionState();
}

class _DemoNavegacionState extends State<_DemoNavegacion> {
  int _indiceActual = 0;

  static const _paginas = [
    Center(child: Text('Página de Inicio')),
    Center(child: Text('Página de Buscar')),
    Center(child: Text('Página de Perfil')),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Demo: navegación inferior')),
      body: _paginas[_indiceActual],
      bottomNavigationBar: NavigationBar(
        selectedIndex: _indiceActual,
        onDestinationSelected: (i) => setState(() => _indiceActual = i),
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: 'Inicio'),
          NavigationDestination(icon: Icon(Icons.search), label: 'Buscar'),
          NavigationDestination(icon: Icon(Icons.person), label: 'Perfil'),
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