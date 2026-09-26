package mx.edu.ipn.uicatalog.ui.section5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import mx.edu.ipn.uicatalog.R
import mx.edu.ipn.uicatalog.databinding.BottomSheetInfoBinding
import mx.edu.ipn.uicatalog.databinding.FragmentSection5Binding

class Section5Fragment : Fragment() {

    private var _binding: FragmentSection5Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSection5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Imagen desde URL
        Glide.with(this)
            .load("https://picsum.photos/seed/uicatalog/400/300")
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(binding.ivUrl)

        // Progreso determinado
        binding.btnIniciarProgreso.setOnClickListener {
            binding.progresoLineal.progress = 0
            binding.btnIniciarProgreso.isEnabled = false
            animarProgreso()
        }

        // Toast / snackbar
        binding.btnToast.setOnClickListener {
            Toast.makeText(requireContext(), "Este es un mensaje toast", Toast.LENGTH_SHORT).show()
        }
        binding.btnSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Cambios guardados", Snackbar.LENGTH_LONG)
                .setAction("Deshacer") {
                    Toast.makeText(requireContext(), "Cambios revertidos", Toast.LENGTH_SHORT).show()
                }.show()
        }

        // Diálogo de confirmación
        binding.btnDialogo.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("¿Eliminar cuenta?")
                .setMessage("Esta acción no se puede deshacer. ¿Deseas continuar?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Eliminar") { _, _ ->
                    Toast.makeText(requireContext(), "Cuenta eliminada (simulado)", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        // Bottom sheet
        binding.btnBottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val sheetBinding = BottomSheetInfoBinding.inflate(layoutInflater)
            dialog.setContentView(sheetBinding.root)
            sheetBinding.btnCerrarSheet.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

    private fun animarProgreso() {
        val progreso = binding.progresoLineal
        progreso.postDelayed(object : Runnable {
            override fun run() {
                if (_binding == null) return
                val nuevo = (progreso.progress + 10).coerceAtMost(100)
                progreso.setProgressCompat(nuevo, true)
                if (nuevo < 100) {
                    progreso.postDelayed(this, 150)
                } else {
                    binding.btnIniciarProgreso.isEnabled = true
                }
            }
        }, 150)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
