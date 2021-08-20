package ibm2187.dev.pixabay.common.utils.ext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.Adapter<*>> RecyclerView.init(
    adp: T,
    isHorizontal: Boolean = false,
    layoutManager: LinearLayoutManager? = null
) {
    val orientation = if (isHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL
    val lm = layoutManager
        ?: LinearLayoutManager(context, orientation, false)

    this.adapter = adp
    this.layoutManager = lm
    this.setHasFixedSize(true)
}

fun <T : RecyclerView.Adapter<*>> RecyclerView.initGrid(
    adp: T,
    layoutManager: GridLayoutManager
) {
    this.adapter = adp
    this.layoutManager = layoutManager
    this.setHasFixedSize(true)
}
