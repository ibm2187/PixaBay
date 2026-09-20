package dev.ibm2187.pixabay.design.component.imagecard

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.ibm2187.pixabay.design.component.image.ImageComposable
import dev.ibm2187.pixabay.design.component.image.ImageUiModel

@Immutable
data class ImageCardUiModel(
    val title: String,
    val description: String,
    val image: ImageUiModel,
)

sealed class ImageCardActions {
    abstract val postId: String

    data class ToggleFavorite(override val postId: String) : ImageCardActions()
}

@Composable
fun ImageCardComposable(
    uiModel: ImageCardUiModel,
    modifier: Modifier = Modifier,
) {

    Column {
        ImageComposable(uiModel = uiModel.image)
    }

}

@Preview
@Composable
fun ImageCardComposablePreview() {
    ImageCardComposable(
        uiModel = ImageCardUiModel(
            title = "Title",
            description = "Description",
            image = ImageUiModel(
                url = "",
                fallback = android.R.drawable.ic_menu_gallery,
                contentDescription = null,
            ),
        )
    )
}
