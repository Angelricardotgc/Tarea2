import 'package:flutter/material.dart';
import '../estado_compartido.dart';
import '../widgets/app_drawer.dart';

class Seccion4Listas extends StatelessWidget {
  const Seccion4Listas({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Listas y colecciones')),
      drawer: const AppDrawer(currentIndex: 3),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: const [
          _ElementoDemo(
            titulo: 'Lista vertical (15+ elementos)',
            explicacion: 'Muestra una colección larga de elementos con desplazamiento propio. '
                'Los nombres que agregues en el campo de texto de la Sección 1 aparecen aquí '
                'automáticamente (conexión entre secciones).',
            demo: SizedBox(height: 280, child: _ListaVerticalDemo()),
          ),
          _ElementoDemo(
            titulo: 'Cuadrícula de elementos',
            explicacion: 'Organiza los elementos en columnas y filas, útil para contenido visual como galerías.',
            demo: SizedBox(height: 280, child: _CuadriculaDemo()),
          ),
          _ElementoDemo(
            titulo: 'Lista con encabezados de sección',
            explicacion: 'Agrupa elementos bajo títulos de sección, combinando dos tipos de elemento distintos (encabezado y tarjeta).',
            demo: SizedBox(height: 280, child: _ListaConEncabezadosDemo()),
          ),
          _ElementoDemo(
            titulo: 'Selección con detalle',
            explicacion: 'Al tocar un elemento de la lista, se abre una ventana con su información completa.',
            demo: SizedBox(height: 200, child: _ListaConDetalleDemo()),
          ),
          _ElementoDemo(
            titulo: 'Deslizar para eliminar',
            explicacion: 'Desliza cualquier elemento hacia un lado para quitarlo de la lista.',
            demo: SizedBox(height: 220, child: _ListaEliminableDemo()),
          ),
          _ElementoDemo(
            titulo: 'Actualizar arrastrando hacia abajo',
            explicacion: 'Arrastra la lista hacia abajo para simular la recarga de nuevos datos.',
            demo: SizedBox(height: 220, child: _ListaConRefrescoDemo()),
          ),
          _ElementoDemo(
            titulo: 'Estado vacío',
            explicacion: 'Cuando no hay elementos que mostrar, se despliega un mensaje e ilustración en vez de una lista en blanco.',
            demo: SizedBox(height: 220, child: _EstadoVacioDemo()),
          ),
          _ElementoDemo(
            titulo: 'Pestañas con contenido deslizable',
            explicacion: 'Permite cambiar entre distintos grupos de contenido tocando una pestaña o deslizando entre ellas.',
            demo: SizedBox(height: 260, child: _PestanasDemo()),
          ),
        ],
      ),
    );
  }
}

// ---------- 1. Lista vertical (recibe datos de la Sección 1) ----------
class _ListaVerticalDemo extends StatelessWidget {
  const _ListaVerticalDemo();

  @override
  Widget build(BuildContext context) {
    return ValueListenableBuilder<List<String>>(
      valueListenable: nombresCapturados,
      builder: (context, capturados, _) {
        final total = 15 + capturados.length;
        return ListView.builder(
          itemCount: total,
          itemBuilder: (context, index) {
            if (index < 15) {
              return ListTile(
                leading: CircleAvatar(child: Text('${index + 1}')),
                title: Text('Elemento ${index + 1}'),
              );
            }
            final nombre = capturados[index - 15];
            return ListTile(
              leading: const CircleAvatar(
                backgroundColor: Colors.green,
                child: Icon(Icons.link, color: Colors.white, size: 18),
              ),
              title: Text(nombre),
              subtitle: const Text('Agregado desde la Sección 1'),
            );
          },
        );
      },
    );
  }
}

// ---------- 2. Cuadrícula ----------
class _CuadriculaDemo extends StatelessWidget {
  const _CuadriculaDemo();

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 3,
        mainAxisSpacing: 8,
        crossAxisSpacing: 8,
      ),
      itemCount: 9,
      itemBuilder: (context, index) => Container(
        decoration: BoxDecoration(
          color: Theme.of(context).colorScheme.primaryContainer,
          borderRadius: BorderRadius.circular(8),
        ),
        alignment: Alignment.center,
        child: Text('${index + 1}'),
      ),
    );
  }
}

// ---------- 3. Lista con encabezados ----------
class _ListaConEncabezadosDemo extends StatelessWidget {
  const _ListaConEncabezadosDemo();

  @override
  Widget build(BuildContext context) {
    final frutas = ['Manzana', 'Mango', 'Pera'];
    final verduras = ['Zanahoria', 'Espinaca'];

    return ListView(
      children: [
        const Padding(
          padding: EdgeInsets.symmetric(vertical: 8),
          child: Text('Frutas', style: TextStyle(fontWeight: FontWeight.bold)),
        ),
        ...frutas.map((f) => ListTile(leading: const Icon(Icons.eco), title: Text(f))),
        const Divider(),
        const Padding(
          padding: EdgeInsets.symmetric(vertical: 8),
          child: Text('Verduras', style: TextStyle(fontWeight: FontWeight.bold)),
        ),
        ...verduras.map((v) => ListTile(leading: const Icon(Icons.grass), title: Text(v))),
      ],
    );
  }
}

// ---------- 4. Selección abre detalle ----------
class _ListaConDetalleDemo extends StatelessWidget {
  const _ListaConDetalleDemo();

  @override
  Widget build(BuildContext context) {
    final productos = ['Laptop', 'Teclado', 'Mouse', 'Monitor'];
    return ListView.builder(
      itemCount: productos.length,
      itemBuilder: (context, index) => ListTile(
        leading: const Icon(Icons.inventory_2),
        title: Text(productos[index]),
        trailing: const Icon(Icons.chevron_right),
        onTap: () {
          showDialog(
            context: context,
            builder: (_) => AlertDialog(
              title: Text(productos[index]),
              content: Text('Detalle completo de "${productos[index]}". Aquí iría información como precio, descripción, etc.'),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: const Text('Cerrar'),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}

// ---------- 5. Deslizar para eliminar ----------
class _ListaEliminableDemo extends StatefulWidget {
  const _ListaEliminableDemo();

  @override
  State<_ListaEliminableDemo> createState() => _ListaEliminableDemoState();
}

class _ListaEliminableDemoState extends State<_ListaEliminableDemo> {
  List<String> _items = List.generate(6, (i) => 'Tarea ${i + 1}');

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: _items.length,
      itemBuilder: (context, index) {
        final item = _items[index];
        return Dismissible(
          key: ValueKey(item),
          direction: DismissDirection.endToStart,
          background: Container(
            color: Colors.red,
            alignment: Alignment.centerRight,
            padding: const EdgeInsets.only(right: 20),
            child: const Icon(Icons.delete, color: Colors.white),
          ),
          onDismissed: (_) {
            setState(() => _items.removeAt(index));
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(content: Text('"$item" eliminado'), duration: const Duration(seconds: 1)),
            );
          },
          child: ListTile(leading: const Icon(Icons.task_alt), title: Text(item)),
        );
      },
    );
  }
}

// ---------- 6. Pull to refresh ----------
class _ListaConRefrescoDemo extends StatefulWidget {
  const _ListaConRefrescoDemo();

  @override
  State<_ListaConRefrescoDemo> createState() => _ListaConRefrescoDemoState();
}

class _ListaConRefrescoDemoState extends State<_ListaConRefrescoDemo> {
  List<String> _items = ['Dato 1', 'Dato 2', 'Dato 3'];
  int _contador = 3;

  Future<void> _refrescar() async {
    await Future.delayed(const Duration(seconds: 1));
    setState(() {
      _contador++;
      _items.insert(0, 'Dato nuevo $_contador');
    });
  }

  @override
  Widget build(BuildContext context) {
    return RefreshIndicator(
      onRefresh: _refrescar,
      child: ListView.builder(
        itemCount: _items.length,
        itemBuilder: (context, index) => ListTile(
          leading: const Icon(Icons.cloud_download),
          title: Text(_items[index]),
        ),
      ),
    );
  }
}

// ---------- 7. Estado vacío ----------
class _EstadoVacioDemo extends StatefulWidget {
  const _EstadoVacioDemo();

  @override
  State<_EstadoVacioDemo> createState() => _EstadoVacioDemoState();
}

class _EstadoVacioDemoState extends State<_EstadoVacioDemo> {
  bool _vacio = true;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Align(
          alignment: Alignment.centerRight,
          child: TextButton(
            onPressed: () => setState(() => _vacio = !_vacio),
            child: Text(_vacio ? 'Agregar elementos' : 'Vaciar lista'),
          ),
        ),
        Expanded(
          child: _vacio
              ? const Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Icon(Icons.inbox, size: 48, color: Colors.grey),
                      SizedBox(height: 8),
                      Text('No hay elementos todavía', style: TextStyle(color: Colors.grey)),
                    ],
                  ),
                )
              : ListView(
                  children: const [
                    ListTile(leading: Icon(Icons.check), title: Text('Elemento A')),
                    ListTile(leading: Icon(Icons.check), title: Text('Elemento B')),
                  ],
                ),
        ),
      ],
    );
  }
}

// ---------- 8. Pestañas deslizables ----------
class _PestanasDemo extends StatelessWidget {
  const _PestanasDemo();

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Column(
        children: [
          const TabBar(
            tabs: [
              Tab(text: 'Todos'),
              Tab(text: 'Activos'),
              Tab(text: 'Archivados'),
            ],
          ),
          Expanded(
            child: TabBarView(
              children: [
                ListView(children: const [
                  ListTile(title: Text('Elemento 1')),
                  ListTile(title: Text('Elemento 2')),
                ]),
                ListView(children: const [
                  ListTile(title: Text('Activo 1')),
                ]),
                ListView(children: const [
                  ListTile(title: Text('Archivado 1')),
                ]),
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