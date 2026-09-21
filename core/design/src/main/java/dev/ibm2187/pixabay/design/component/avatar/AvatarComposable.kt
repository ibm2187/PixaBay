package dev.ibm2187.pixabay.design.component.avatar

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import dev.ibm2187.pixabay.design.component.image.ImageComposable
import dev.ibm2187.pixabay.design.component.image.ImageUiModel
import dev.ibm2187.pixabay.design.theme.PixabayTheme

@Composable
fun AvatarComposable(
    uiModel: ImageUiModel,
    modifier: Modifier = Modifier,
) {
    ImageComposable(
        uiModel = uiModel,
        modifier = modifier
            .size(PixabayTheme.dimens.avatarSize)
            .clip(CircleShape),
    )
}

@Preview
@Composable
private fun AvatarComposablePreview() {
    PixabayTheme {
        AvatarComposable(
            uiModel = ImageUiModel(
                url = "",
                fallback = android.R.drawable.ic_menu_gallery,
                contentDescription = null,
            )
        )
    }
}
