package mx.edu.ipn.uicatalog.ui.section2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import mx.edu.ipn.uicatalog.databinding.FragmentSection2Binding

class Section2Fragment : Fragment() {

    private var _binding: FragmentSection2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSection2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRelleno.setOnClickListener { binding.tvRespuestaBasicos.text = "Pulsaste: Relleno" }
        binding.btnContorno.setOnClickListener { binding.tvRespuestaBasicos.text = "Pulsaste: Contorno" }
        binding.btnTexto.setOnClickListener { binding.tvRespuestaBasicos.text = "Pulsaste: Texto" }

        binding.btnSoloIcono.setOnClickListener { binding.tvRespuestaIconos.text = "Ícono presionado" }
        binding.btnIconoTexto.setOnClickListener {
            binding.tvRespuestaIconos.text = "Compartiendo contenido…"
            Toast.makeText(requireContext(), "Compartido", Toast.LENGTH_SHORT).show()
        }

        binding.fabNormal.setOnClickListener { binding.tvRespuestaFab.text = "FAB normal presionado" }
        binding.fabExtendido.setOnClickListener {
            binding.tvRespuestaFab.text = "Creando elemento nuevo…"
            Snackbar.make(binding.root, "Elemento creado", Snackbar.LENGTH_SHORT)
                .setAction("Deshacer") { binding.tvRespuestaFab.text = "Creación deshecha" }
                .show()
        }

        binding.toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                val texto = when (checkedId) {
                    binding.btnDia.id -> "Día"
                    binding.btnSemana.id -> "Semana"
                    else -> "Mes"
                }
                binding.tvRespuestaToggle.text = "Vista seleccionada: $texto"
            }
        }
        binding.btnDia.isChecked = true

        binding.btnCarga.setOnClickListener {
            binding.btnCarga.isEnabled = false
            binding.progressCarga.visibility = View.VISIBLE
            binding.btnCarga.text = "Guardando…"
            binding.root.postDelayed({
                if (_binding == null) return@postDelayed
                binding.progressCarga.visibility = View.GONE
                binding.btnCarga.isEnabled = true
                binding.btnCarga.text = "Guardar"
                Toast.makeText(requireContext(), "Guardado con éxito", Toast.LENGTH_SHORT).show()
            }, 1500)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
