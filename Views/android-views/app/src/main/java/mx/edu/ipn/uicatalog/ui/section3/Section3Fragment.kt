package mx.edu.ipn.uicatalog.ui.section3

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import mx.edu.ipn.uicatalog.databinding.FragmentSection3Binding
import java.util.Calendar

class Section3Fragment : Fragment() {

    private var _binding: FragmentSection3Binding? = null
    private val binding get() = _binding!!
    private var actualizandoPadre = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSection3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Checkbox con estado indeterminado simulado
        val hijas = listOf(binding.cbHija1, binding.cbHija2)
        fun actualizarPadre() {
            actualizandoPadre = true
            val marcadas = hijas.count { it.isChecked }
            binding.cbPadre.isChecked = marcadas == hijas.size
            // No existe estado indeterminado nativo en CheckBox estándar, se simula con alpha
            binding.cbPadre.alpha = if (marcadas in 1 until hijas.size) 0.5f else 1f
            actualizandoPadre = false
        }
        hijas.forEach { it.setOnCheckedChangeListener { _, _ -> actualizarPadre() } }
        binding.cbPadre.setOnCheckedChangeListener { _, checked ->
            if (!actualizandoPadre) hijas.forEach { it.isChecked = checked }
        }

        // RadioGroup
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val texto = when (checkedId) {
                binding.rbTarjeta.id -> "Tarjeta de crédito"
                binding.rbEfectivo.id -> "Efectivo"
                else -> "Transferencia"
            }
            binding.tvRadioResultado.text = "Método de pago: $texto"
        }
        binding.tvRadioResultado.text = "Método de pago: Tarjeta de crédito"

        // Slider simple
        binding.sliderSimple.addOnChangeListener { _, value, _ ->
            binding.tvSliderValor.text = "Volumen: ${value.toInt()}"
        }

        // Range slider
        binding.rangeSlider.setValues(200f, 800f)
        binding.rangeSlider.addOnChangeListener { slider, _, _ ->
            val (min, max) = slider.values
            binding.tvRangoValor.text = "Precio: $${min.toInt()} - $${max.toInt()}"
        }

        // Dropdown
        val opcionesEnvio = listOf("Estándar (5 días)", "Express (2 días)", "Mismo día")
        binding.actvEnvio.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, opcionesEnvio)
        )

        // Date picker
        binding.btnFecha.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(requireContext(), { _, y, m, d ->
                binding.tvFechaHora.text = "Fecha seleccionada: %02d/%02d/%d".format(d, m + 1, y)
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Time picker
        binding.btnHora.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12).setMinute(0)
                .setTitleText("Selecciona una hora")
                .build()
            picker.addOnPositiveButtonClickListener {
                binding.tvFechaHora.text = "Hora seleccionada: %02d:%02d".format(picker.hour, picker.minute)
            }
            picker.show(childFragmentManager, "time_picker")
        }

        // Chips de filtro
        val chips = listOf(binding.chipEconomico, binding.chipRapido, binding.chipEcologico)
        chips.forEach { chip: Chip ->
            chip.setOnCheckedChangeListener { _, _ ->
                val seleccionados = chips.filter { it.isChecked }.map { it.text }
                binding.tvChipsResultado.text =
                    if (seleccionados.isEmpty()) "Ningún filtro activo"
                    else "Filtros activos: ${seleccionados.joinToString(", ")}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
