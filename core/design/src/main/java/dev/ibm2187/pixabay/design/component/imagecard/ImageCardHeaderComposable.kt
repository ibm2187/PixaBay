package dev.ibm2187.pixabay.design.component.imagecard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import dev.ibm2187.pixabay.design.component.avatar.AvatarComposable
import dev.ibm2187.pixabay.design.component.image.ImageUiModel
import dev.ibm2187.pixabay.design.theme.PixabayTheme

@Immutable
internal data class ImageCardHeaderUiModel(
    val userName: String,
    val avatar: ImageUiModel,
)

@Composable
internal fun ImageCardHeaderComposable(
    uiModel: ImageCardHeaderUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(PixabayTheme.dimens.cardPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PixabayTheme.dimens.cardSpacing),
    ) {
        AvatarComposable(uiModel = uiModel.avatar)

        Text(
            text = uiModel.userName,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
private fun ImageCardHeaderComposablePreview() {
    PixabayTheme {
        ImageCardHeaderComposable(
            uiModel = ImageCardHeaderUiModel(
                userName = "ibrahim",
                avatar = ImageUiModel(
                    url = "",
                    fallback = android.R.drawable.ic_menu_gallery,
                    contentDescription = null,
                ),
            )
        )
    }
}
