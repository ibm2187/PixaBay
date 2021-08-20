package ibm2187.dev.pixabay.common.base.ui

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<V : ViewBinding> : BottomSheetDialogFragment() {

    abstract val _bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> V

    private var _binding: V? = null
    val binding get() = _binding!!

    private var fullScreenDialog = false

    fun setFullScreenDialog() {
        fullScreenDialog = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = _bindingInflater(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        dialog?.setOnDismissListener(null)
        dialog?.setOnCancelListener(null)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (!fullScreenDialog)
            super.onCreateDialog(savedInstanceState)
        else {
            val dialog: Dialog = super.onCreateDialog(savedInstanceState)
            dialog.setOnShowListener { dialogInterface ->
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                setupFullHeight(bottomSheetDialog)
            }
            dialog
        }
    }


    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
                ?: return
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.isHideable = false
        behavior.isDraggable = false
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        activity?.let {
            it.windowManager.defaultDisplay.getMetrics(displayMetrics)
        }
        return displayMetrics.heightPixels
    }

    fun <T> LiveData<T>.observe(observer: Observer<T>) {
        this.observe(viewLifecycleOwner, observer)
    }
}