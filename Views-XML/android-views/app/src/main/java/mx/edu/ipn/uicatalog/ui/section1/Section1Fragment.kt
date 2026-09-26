package mx.edu.ipn.uicatalog.ui.section1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import mx.edu.ipn.uicatalog.data.SharedViewModel
import mx.edu.ipn.uicatalog.databinding.FragmentSection1Binding

class Section1Fragment : Fragment() {

    private var _binding: FragmentSection1Binding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val frutas = listOf(
        "Manzana", "Plátano", "Naranja", "Fresa", "Uva",
        "Mango", "Piña", "Sandía", "Kiwi", "Papaya"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSection1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Campo simple -> conecta con Sección 4 vía SharedViewModel
        binding.btnAgregarFavorito.setOnClickListener {
            val texto = binding.etSimple.text?.toString()?.trim().orEmpty()
            if (texto.isNotEmpty()) {
                sharedViewModel.agregarFavorito(texto)
                binding.etSimple.text?.clear()
                Toast.makeText(requireContext(), "\"$texto\" agregado a favoritos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Escribe un nombre primero", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Validación en tiempo real
        binding.etValidacion.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.tilValidacion.error =
                    if ((s?.length ?: 0) in 1..3) "Se requieren al menos 4 caracteres" else null
            }
        })

        // 6. Autocompletado
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listOf(
            "Aguascalientes", "Baja California", "Ciudad de México", "Jalisco", "Nuevo León", "Puebla", "Yucatán"
        ))
        binding.actvAutocompletado.setAdapter(adapter)

        // 7. Búsqueda en tiempo real
        actualizarResultadoBusqueda("")
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(newText: String?): Boolean {
                actualizarResultadoBusqueda(newText.orEmpty())
                return true
            }
        })
    }

    private fun actualizarResultadoBusqueda(query: String) {
        val filtradas = frutas.filter { it.contains(query, ignoreCase = true) }
        binding.tvResultadoBusqueda.text =
            if (filtradas.isEmpty()) "Sin resultados" else "Resultados: ${filtradas.joinToString(", ")}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
