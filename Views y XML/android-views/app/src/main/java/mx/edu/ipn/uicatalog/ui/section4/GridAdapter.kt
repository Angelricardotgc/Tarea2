package mx.edu.ipn.uicatalog.ui.section4

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ipn.uicatalog.databinding.ItemGridBinding

data class CategoriaGrid(val icono: String, val nombre: String)

class GridAdapter(private val items: List<CategoriaGrid>) :
    RecyclerView.Adapter<GridAdapter.VH>() {

    inner class VH(val binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.binding.tvGridIcono.text = item.icono
        holder.binding.tvGridTexto.text = item.nombre
        holder.binding.root.setOnClickListener {
            Toast.makeText(holder.binding.root.context, "Categoría: ${item.nombre}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = items.size
}
