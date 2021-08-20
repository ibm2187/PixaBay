package ibm2187.dev.pixabay.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ibm2187.dev.pixabay.R
import ibm2187.dev.pixabay.common.base.ui.BaseRvAdapter
import ibm2187.dev.pixabay.common.utils.ext.inflateRvItem
import ibm2187.dev.pixabay.databinding.ItemImageMainBinding
import ibm2187.dev.pixabay.model.network.dto.Hit

class PixaBayImagesAdapter(
    val clickListener: (Hit) -> Unit
) : BaseRvAdapter<Hit, PixaBayImagesAdapter.PixaBayViewHolder>() {

    inner class PixaBayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemImageMainBinding.bind(itemView)
        fun bindView(hit: Hit) {
            //todo introduce a card view listener
            binding.root.setOnClickListener {
                clickListener.invoke(hit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaBayViewHolder {
        return PixaBayViewHolder(parent.inflateRvItem(R.layout.item_image_main))
    }

    override fun onBindViewHolder(holder: PixaBayViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}