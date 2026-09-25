import 'package:flutter/material.dart';
import '../main.dart';
import '../screens/seccion1_entrada_texto.dart';
import '../screens/seccion2_botones.dart';
import '../screens/seccion3_seleccion.dart';
import '../screens/seccion4_listas.dart';
import '../screens/seccion5_info.dart';
import '../screens/seccion6_contenedores.dart';

/// Menú lateral disponible en todas las pantallas.
/// currentIndex: -1 = Inicio, 0 a 5 = Secciones 1 a 6.
class AppDrawer extends StatelessWidget {
  final int currentIndex;
  const AppDrawer({super.key, required this.currentIndex});

  static const List<(String, IconData)> _secciones = [
    ('Entrada de texto', Icons.text_fields),
    ('Botones y acciones', Icons.smart_button),
    ('Elementos de selección', Icons.checklist),
    ('Listas y colecciones', Icons.list),
    ('Información y retroalimentación', Icons.info),
    ('Contenedores y estructura', Icons.dashboard),
  ];

  void _navegar(BuildContext context, int index) {
    if (index == currentIndex) {
      Navigator.pop(context);
      return;
    }
    late final Widget destino;
    switch (index) {
      case -1:
        destino = const PantallaPrincipal();
        break;
      case 0:
        destino = const Seccion1EntradaTexto();
        break;
      case 1:
        destino = const Seccion2Botones();
        break;
      case 2:
        destino = const Seccion3Seleccion();
        break;
      case 3:
        destino = const Seccion4Listas();
        break;
      case 4:
        destino = const Seccion5Info();
        break;
      case 5:
        destino = const Seccion6Contenedores();
        break;
      default:
        destino = const PantallaPrincipal();
    }
    Navigator.pushReplacement(context, MaterialPageRoute(builder: (_) => destino));
  }

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: [
          DrawerHeader(
            decoration: BoxDecoration(color: Theme.of(context).colorScheme.primary),
            child: const Align(
              alignment: Alignment.bottomLeft,
              child: Text(
                'Catálogo de UI',
                style: TextStyle(color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold),
              ),
            ),
          ),
          ListTile(
            leading: const Icon(Icons.home),
            title: const Text('Inicio'),
            selected: currentIndex == -1,
            onTap: () => _navegar(context, -1),
          ),
          const Divider(),
          for (int i = 0; i < _secciones.length; i++)
            ListTile(
              leading: Icon(_secciones[i].$2),
              title: Text(_secciones[i].$1),
              selected: currentIndex == i,
              onTap: () => _navegar(context, i),
            ),
        ],
      ),
    );
  }
}