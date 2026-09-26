package mx.edu.ipn.uicatalog.ui.section4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ipn.uicatalog.databinding.ItemHeaderBinding
import mx.edu.ipn.uicatalog.databinding.ItemSimpleRowBinding

sealed class FilaContacto {
    data class Encabezado(val letra: String) : FilaContacto()
    data class Contacto(val nombre: String) : FilaContacto()
}

private const val TIPO_ENCABEZADO = 0
private const val TIPO_CONTACTO = 1

class HeaderListAdapter(private val filas: List<FilaContacto>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class HeaderVH(val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root)
    class ContactoVH(val binding: ItemSimpleRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int) = when (filas[position]) {
        is FilaContacto.Encabezado -> TIPO_ENCABEZADO
        is FilaContacto.Contacto -> TIPO_CONTACTO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TIPO_ENCABEZADO) {
            HeaderVH(ItemHeaderBinding.inflate(inflater, parent, false))
        } else {
            ContactoVH(ItemSimpleRowBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val fila = filas[position]) {
            is FilaContacto.Encabezado -> (holder as HeaderVH).binding.tvHeader.text = fila.letra
            is FilaContacto.Contacto -> (holder as ContactoVH).binding.tvRow.text = "  ${fila.nombre}"
        }
    }

    override fun getItemCount() = filas.size
}
