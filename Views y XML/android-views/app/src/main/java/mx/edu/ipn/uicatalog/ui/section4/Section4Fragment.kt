package mx.edu.ipn.uicatalog.ui.section4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import mx.edu.ipn.uicatalog.data.SharedViewModel
import mx.edu.ipn.uicatalog.databinding.FragmentSection4Binding

class Section4Fragment : Fragment() {

    private var _binding: FragmentSection4Binding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var verticalAdapter: SimpleListAdapter
    private var favoritosActuales = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSection4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarListaVertical()
        configurarCuadricula()
        configurarEncabezados()
        configurarSwipe()
        configurarPullToRefresh()
        configurarEstadoVacio()
        configurarPestanas()
    }

    // 1. Lista vertical (15+ elementos) + conexión con Sección 1 + detalle al tocar
    private fun configurarListaVertical() {
        val baseItems = (1..15).map { "Elemento de la lista #$it" }.toMutableList()
        verticalAdapter = SimpleListAdapter(
            items = baseItems,
            favoritosCount = { favoritosActuales },
            onClick = { texto ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Detalle")
                    .setMessage("Elemento seleccionado:\n\n$texto")
                    .setPositiveButton("Cerrar", null)
                    .show()
            }
        )
        binding.rvVertical.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVertical.adapter = verticalAdapter

        sharedViewModel.favoritos.observe(viewLifecycleOwner) { favoritos ->
            favoritosActuales = favoritos.size
            val actualizado = favoritos + (1..15).map { "Elemento de la lista #$it" }
            verticalAdapter.setAll(actualizado)
        }
    }

    // 2. Cuadrícula
    private fun configurarCuadricula() {
        val categorias = listOf(
            CategoriaGrid("🍔", "Comida"), CategoriaGrid("🚗", "Transporte"),
            CategoriaGrid("🎬", "Cine"), CategoriaGrid("🛒", "Compras"),
            CategoriaGrid("💊", "Salud"), CategoriaGrid("🎓", "Educación")
        )
        binding.rvGrid.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvGrid.adapter = GridAdapter(categorias)
    }

    // 3. Encabezados de sección
    private fun configurarEncabezados() {
        val filas = mutableListOf<FilaContacto>()
        val contactos = listOf(
            "A" to listOf("Ana", "Andrés"),
            "C" to listOf("Carlos", "Camila"),
            "L" to listOf("Luis", "Lorena"),
            "M" to listOf("María", "Mateo")
        )
        contactos.forEach { (letra, nombres) ->
            filas.add(FilaContacto.Encabezado(letra))
            nombres.forEach { filas.add(FilaContacto.Contacto(it)) }
        }
        binding.rvEncabezados.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEncabezados.adapter = HeaderListAdapter(filas)
    }

    // 4. Swipe to delete
    private fun configurarSwipe() {
        val tareas = mutableListOf(
            "Comprar despensa", "Enviar reporte", "Llamar al dentista",
            "Revisar correos", "Pagar servicios", "Estudiar para el examen"
        )
        val adapter = SimpleListAdapter(tareas)
        binding.rvSwipe.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSwipe.adapter = adapter

        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.bindingAdapterPosition)
            }
        })
        touchHelper.attachToRecyclerView(binding.rvSwipe)
    }

    // 5. Pull to refresh
    private fun configurarPullToRefresh() {
        var contador = 0
        val items = mutableListOf("Publicación 1", "Publicación 2", "Publicación 3")
        val adapter = SimpleListAdapter(items)
        binding.rvRefresh.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRefresh.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            binding.rvRefresh.postDelayed({
                if (_binding == null) return@postDelayed
                contador++
                adapter.addAtTop("Publicación nueva #$contador")
                binding.swipeRefresh.isRefreshing = false
            }, 1000)
        }
    }

    // 6. Estado vacío
    private fun configurarEstadoVacio() {
        val items = mutableListOf("Foto 1", "Foto 2", "Foto 3", "Foto 4")
        val adapter = SimpleListAdapter(items)
        binding.rvVacioLista.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVacioLista.adapter = adapter

        fun actualizarVisibilidad() {
            val vacio = items.isEmpty()
            binding.layoutEstadoVacio.visibility = if (vacio) View.VISIBLE else View.GONE
            binding.rvVacioLista.visibility = if (vacio) View.GONE else View.VISIBLE
            binding.btnToggleVacio.text = if (vacio) "Restaurar elementos" else "Vaciar lista"
        }

        binding.btnToggleVacio.setOnClickListener {
            if (items.isEmpty()) {
                items.addAll(listOf("Foto 1", "Foto 2", "Foto 3", "Foto 4"))
            } else {
                items.clear()
            }
            adapter.setAll(items)
            actualizarVisibilidad()
        }
        actualizarVisibilidad()
    }

    // 7. Pestañas deslizables
    private fun configurarPestanas() {
        val textos = listOf(
            "Contenido de la pestaña Resumen",
            "Contenido de la pestaña Detalles",
            "Contenido de la pestaña Ajustes"
        )
        binding.viewPager.adapter = TabPagerAdapter(textos)
        val titulos = listOf("Resumen", "Detalles", "Ajustes")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titulos[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
