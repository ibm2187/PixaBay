package dev.ibm2187.pixabay.design.component.imagecard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.ibm2187.pixabay.design.component.image.ImageComposable
import dev.ibm2187.pixabay.design.component.image.ImageUiModel
import dev.ibm2187.pixabay.design.theme.PixabayTheme

@Immutable
data class ImageCardUiModel(
    val postId: String,
    val userName: String,
    val userAvatar: ImageUiModel,
    val image: ImageUiModel,
    val caption: String,
    val likes: String,
    val isFavorite: Boolean,
)

@Composable
fun ImageCardComposable(
    uiModel: ImageCardUiModel,
    modifier: Modifier = Modifier,
    onAction: (ImageCardAction) -> Unit = {},
) {
    val actionsUiModel = rememberImageCardActions(uiModel)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(PixabayTheme.dimens.cardSpacing),
    ) {
        ImageCardHeaderComposable(
            uiModel = remember(uiModel.userName, uiModel.userAvatar) {
                ImageCardHeaderUiModel(
                    userName = uiModel.userName,
                    avatar = uiModel.userAvatar,
                )
            }
        )

        ImageComposable(
            uiModel = uiModel.image,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
        )

        ImageCardActionsComposable(
            uiModel = actionsUiModel,
            onAction = onAction,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PixabayTheme.dimens.cardSpacing),
        )

        ImageCardCaptionComposable(
            uiModel = remember(uiModel.likes, uiModel.userName, uiModel.caption) {
                ImageCardCaptionUiModel(
                    likes = uiModel.likes,
                    userName = uiModel.userName,
                    caption = uiModel.caption,
                )
            },
            modifier = Modifier.padding(bottom = PixabayTheme.dimens.cardPadding),
        )
    }
}

@Preview
@Composable
private fun ImageCardComposablePreview() {
    PixabayTheme {
        ImageCardComposable(
            uiModel = ImageCardUiModel(
                postId = "123",
                userName = "ibrahim",
                userAvatar = ImageUiModel(
                    url = "",
                    fallback = android.R.drawable.ic_menu_gallery,
                    contentDescription = null,
                ),
                image = ImageUiModel(
                    url = "",
                    fallback = android.R.drawable.ic_menu_gallery,
                    contentDescription = null,
                ),
                caption = "A long forest road in the early morning fog, shot on a grey autumn day.",
                likes = "1,240 likes",
                isFavorite = false,
            )
        )
    }
}

@Preview
@Composable
private fun ImageCardComposableFavoritedPreview() {
    PixabayTheme {
        ImageCardComposable(
            uiModel = ImageCardUiModel(
                postId = "123",
                userName = "ibrahim",
                userAvatar = ImageUiModel(
                    url = "",
                    fallback = android.R.drawable.ic_menu_gallery,
                    contentDescription = null,
                ),
                image = ImageUiModel(
                    url = "",
                    fallback = android.R.drawable.ic_menu_gallery,
                    contentDescription = null,
                ),
                caption = "Short caption.",
                likes = "12 likes",
                isFavorite = true,
            )
        )
    }
}
