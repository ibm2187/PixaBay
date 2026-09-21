package dev.ibm2187.pixabay.design.component.imagecard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.ibm2187.pixabay.design.theme.PixabayTheme

@Immutable
internal data class ImageCardCaptionUiModel(
    val likes: String,
    val userName: String,
    val caption: String,
)

@Composable
internal fun ImageCardCaptionComposable(
    uiModel: ImageCardCaptionUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = PixabayTheme.dimens.cardPadding),
        verticalArrangement = Arrangement.spacedBy(PixabayTheme.dimens.cardSpacing),
    ) {
        Text(
            text = uiModel.likes,
            style = MaterialTheme.typography.labelLarge,
        )

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append(uiModel.userName)
                }
                append(" ")
                append(uiModel.caption)
            },
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
private fun ImageCardCaptionComposablePreview() {
    PixabayTheme {
        ImageCardCaptionComposable(
            uiModel = ImageCardCaptionUiModel(
                likes = "1,240 likes",
                userName = "ibrahim",
                caption = "A long forest road in the early morning fog, shot on a grey autumn day.",
            )
        )
    }
}
