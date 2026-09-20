package dev.ibm2187.pixabay.design.component.image

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage

@Immutable
data class ImageUiModel(
    val url: String,
    @param:DrawableRes val fallback: Int,
    val contentDescription: String?,
)

@Composable
fun ImageComposable(
    uiModel: ImageUiModel,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val fallback = painterResource(uiModel.fallback)

    AsyncImage(
        model = uiModel.url,
        contentDescription = uiModel.contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        // error covers a failed load, fallback covers a null/blank model
        error = fallback,
        fallback = fallback,
    )
}
