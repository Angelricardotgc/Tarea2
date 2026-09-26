package mx.edu.ipn.uicatalog.ui.section6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import mx.edu.ipn.uicatalog.databinding.FragmentSection6Binding

class Section6Fragment : Fragment() {

    private var _binding: FragmentSection6Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSection6Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.miniToolbar.setOnMenuItemClickListener { item ->
            Toast.makeText(requireContext(), "Acción: ${item.title}", Toast.LENGTH_SHORT).show()
            true
        }

        binding.bottomNavDemo.setOnItemSelectedListener { item ->
            binding.tvBottomNavResultado.text = "Pestaña activa: ${item.title}"
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
