package dev.ibm2187.core.utils.ext

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


val navOptions = NavOptions.Builder()
    .setEnterAnim(dev.ibm2187.pixabay.core.design.R.anim.slide_in_right)
    .setExitAnim(dev.ibm2187.pixabay.core.design.R.anim.slide_out_left)
    .setPopEnterAnim(dev.ibm2187.pixabay.core.design.R.anim.slide_in_left)
    .setPopExitAnim(dev.ibm2187.pixabay.core.design.R.anim.slide_out_right)
    .build()


fun NavDirections.navigateWith(fragment: Fragment) {
    val actionId = this.actionId

    val navController = fragment.findNavController()
    val currentDestination = navController.currentDestination

    val action = currentDestination?.getAction(actionId)
        ?: navController.graph.getAction(actionId)


    if (action != null && currentDestination?.id != action.destinationId)
        navController.navigate(this, navOptions)
}