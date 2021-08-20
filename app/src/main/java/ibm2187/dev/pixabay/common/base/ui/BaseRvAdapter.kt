package ibm2187.dev.pixabay.common.base.ui

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRvAdapter<T, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    private val items = arrayListOf<T>()

    open fun areItemsTheSame(oldItem: T, newItem: T) = oldItem === newItem
    open fun areContentsTheSame(oldItem: T, newItem: T) = oldItem === newItem

    fun getItem(index: Int) = items[index]

    fun setItems(newItems: List<T>) {
        val diffUtilCallback = BaseDiffUtilCallback(newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemId(position: Int): Long = position.hashCode().toLong()

    override fun getItemCount(): Int = items.size


    inner class BaseDiffUtilCallback(private val newItems: List<T>) : DiffUtil.Callback() {
        override fun getOldListSize() = items.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            areItemsTheSame(items[oldItemPosition], newItems[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            areContentsTheSame(items[oldItemPosition], newItems[newItemPosition])

    }

}
