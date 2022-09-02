package dev.ibm2187.core.utils.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow


fun ViewGroup.inflateRvItem(@LayoutRes itemRes: Int): View {
    return LayoutInflater.from(this.context)
        .inflate(itemRes, this, false)
}


fun SearchView.getQueryChangeFlow() = callbackFlow {
    var searchListener: SearchView.OnQueryTextListener? = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            trySend(Pair(query, true))
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            trySend(Pair(newText, true))
            return true
        }


    }

    this@getQueryChangeFlow.setOnQueryTextListener(searchListener)
    awaitClose {
        searchListener = null
        this@getQueryChangeFlow.setOnQueryTextListener(searchListener)
    }

}