package ibm2187.dev.pixabay.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.ibm2187.core.utils.ext.inflateRvItem
import dev.ibm2187.pixabay.core.data.network.responses.Hit
import ibm2187.dev.pixabay.R
import ibm2187.dev.pixabay.databinding.ItemImageMainBinding

class PixaBayImagesAdapter(
    val clickListener: (Hit) -> Unit
) : ListAdapter<Hit, PixaBayImagesAdapter.PixaBayViewHolder>(
    ImageAdapterDiffer()
) {

    inner class PixaBayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemImageMainBinding.bind(itemView)
        fun bindView(hit: Hit) {
            binding.card.setOnClickListener {
                clickListener.invoke(hit)
            }
            binding.image.load(hit.webformatURL)
            binding.user.text = hit.user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaBayViewHolder {
        return PixaBayViewHolder(parent.inflateRvItem(viewType))
    }

    override fun onBindViewHolder(holder: PixaBayViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_image_main
    }
}

private class ImageAdapterDiffer : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem == newItem
    }

}