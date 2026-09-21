package dev.ibm2187.pixabay.design.component.image

import androidx.annotation.DrawableRes
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import dev.ibm2187.pixabay.design.theme.PixabayTheme

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

    SubcomposeAsyncImage(
        model = uiModel.url,
        contentDescription = uiModel.contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        loading = {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        },
        // error covers a failed load and a null/blank model alike
        error = {
            SubcomposeAsyncImageContent(painter = fallback)
        },
    )
}

@Preview
@Composable
private fun ImageComposablePreview() {
    PixabayTheme {
        ImageComposable(
            uiModel = ImageUiModel(
                url = "",
                fallback = android.R.drawable.ic_menu_gallery,
                contentDescription = null,
            )
        )
    }
}
