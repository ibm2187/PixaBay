package dev.ibm2187.pixabay.design.component.icon

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import dev.ibm2187.pixabay.design.icon.FavoriteFilled
import dev.ibm2187.pixabay.design.icon.FavoriteOutlined
import dev.ibm2187.pixabay.design.icon.PixabayIcons
import dev.ibm2187.pixabay.design.theme.PixabayTheme

@Immutable
data class IconUiModel(
    val icon: ImageVector,
    val contentDescription: String?,
)

@Composable
fun IconComposable(
    uiModel: IconUiModel,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = uiModel.icon,
        contentDescription = uiModel.contentDescription,
        modifier = modifier.size(PixabayTheme.dimens.iconSize),
    )
}

@Preview
@Composable
fun IconComposablePreview() {
    PixabayTheme {
        Row {
            IconComposable(
                uiModel = IconUiModel(
                    icon = PixabayIcons.FavoriteOutlined,
                    contentDescription = null,
                )
            )

            IconComposable(
                uiModel = IconUiModel(
                    icon = PixabayIcons.FavoriteFilled,
                    contentDescription = null,
                )
            )
        }
    }
}
