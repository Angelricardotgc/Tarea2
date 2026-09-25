import 'package:flutter/material.dart';
import 'widgets/app_drawer.dart';
import 'screens/seccion1_entrada_texto.dart';
import 'screens/seccion2_botones.dart';
import 'screens/seccion3_seleccion.dart';
import 'screens/seccion4_listas.dart';
import 'screens/seccion5_info.dart';
import 'screens/seccion6_contenedores.dart';

void main() {
  runApp(const CatalogoApp());
}

class CatalogoApp extends StatelessWidget {
  const CatalogoApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Catálogo de UI',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorSchemeSeed: Colors.indigo,
        brightness: Brightness.light,
        useMaterial3: true,
      ),
      darkTheme: ThemeData(
        colorSchemeSeed: Colors.indigo,
        brightness: Brightness.dark,
        useMaterial3: true,
      ),
      themeMode: ThemeMode.system,
      home: const PantallaPrincipal(),
    );
  }
}

class PantallaPrincipal extends StatelessWidget {
  const PantallaPrincipal({super.key});

  static const secciones = [
    ('Entrada de texto', Icons.text_fields),
    ('Botones y acciones', Icons.smart_button),
    ('Elementos de selección', Icons.checklist),
    ('Listas y colecciones', Icons.list),
    ('Información y retroalimentación', Icons.info),
    ('Contenedores y estructura', Icons.dashboard),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Catálogo de elementos UI')),
      drawer: const AppDrawer(currentIndex: -1),
      body: GridView.builder(
        padding: const EdgeInsets.all(16),
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          mainAxisSpacing: 16,
          crossAxisSpacing: 16,
        ),
        itemCount: secciones.length,
        itemBuilder: (context, index) {
          final (titulo, icono) = secciones[index];
          return Card(
            child: InkWell(
              onTap: () {
                late final Widget destino;
                switch (index) {
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
                    return;
                }
                Navigator.pushReplacement(
                  context,
                  MaterialPageRoute(builder: (_) => destino),
                );
              },
              child: Padding(
                padding: const EdgeInsets.all(12),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(icono, size: 40),
                    const SizedBox(height: 8),
                    Text(titulo, textAlign: TextAlign.center),
                  ],
                ),
              ),
            ),
          );
        },
      ),
    );
  }
}