package mx.edu.ipn.uicatalog.ui.section4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ipn.uicatalog.databinding.ItemTabPageBinding

class TabPagerAdapter(private val textos: List<String>) :
    RecyclerView.Adapter<TabPagerAdapter.VH>() {

    inner class VH(val binding: ItemTabPageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemTabPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvTabPage.text = textos[position]
    }

    override fun getItemCount() = textos.size
}
