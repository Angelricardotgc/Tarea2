package mx.edu.ipn.uicatalog.ui.section4

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ipn.uicatalog.databinding.ItemSimpleRowBinding

/**
 * Adapter reutilizable para una lista plana de textos.
 * [favoritosCount] indica cuántos elementos al inicio de la lista provienen
 * de la Sección 1 (conexión entre secciones) y se resaltan visualmente.
 */
class SimpleListAdapter(
    private val items: MutableList<String>,
    private val favoritosCount: () -> Int = { 0 },
    private val onClick: ((String) -> Unit)? = null
) : RecyclerView.Adapter<SimpleListAdapter.VH>() {

    inner class VH(val binding: ItemSimpleRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemSimpleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val texto = items[position]
        val esFavorito = position < favoritosCount()
        holder.binding.tvRow.text = if (esFavorito) "⭐ $texto (agregado en Sección 1)" else texto
        holder.binding.tvRow.setTypeface(null, if (esFavorito) Typeface.BOLD else Typeface.NORMAL)
        holder.binding.root.setOnClickListener { onClick?.invoke(texto) }
    }

    override fun getItemCount() = items.size

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setAll(nuevos: List<String>) {
        items.clear()
        items.addAll(nuevos)
        notifyDataSetChanged()
    }

    fun addAtTop(item: String) {
        items.add(0, item)
        notifyItemInserted(0)
    }
}
