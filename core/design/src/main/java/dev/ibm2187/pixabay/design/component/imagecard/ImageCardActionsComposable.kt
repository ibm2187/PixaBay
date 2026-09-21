package dev.ibm2187.pixabay.design.component.imagecard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.ibm2187.pixabay.design.R
import dev.ibm2187.pixabay.design.component.icon.IconComposable
import dev.ibm2187.pixabay.design.component.icon.IconUiModel
import dev.ibm2187.pixabay.design.icon.FavoriteFilled
import dev.ibm2187.pixabay.design.icon.FavoriteOutlined
import dev.ibm2187.pixabay.design.icon.PixabayIcons
import dev.ibm2187.pixabay.design.theme.PixabayTheme

sealed interface ImageCardAction {
    val postId: String

    data class ToggleFavorite(override val postId: String) : ImageCardAction
}

@Immutable
internal data class ActionIconUiModel(
    val icon: IconUiModel,
    val action: ImageCardAction,
)

@Immutable
internal data class ImageCardActionsUiModel(
    val leading: List<ActionIconUiModel>,
    val trailing: List<ActionIconUiModel>,
)

@Composable
internal fun rememberImageCardActions(uiModel: ImageCardUiModel): ImageCardActionsUiModel {
    val favoriteDescription = stringResource(R.string.action_favorie)

    return remember(uiModel.postId, uiModel.isFavorite, favoriteDescription) {
        ImageCardActionsUiModel(
            leading = listOf(
                ActionIconUiModel(
                    icon = IconUiModel(
                        icon = if (uiModel.isFavorite) {
                            PixabayIcons.FavoriteFilled
                        } else {
                            PixabayIcons.FavoriteOutlined
                        },
                        contentDescription = favoriteDescription,
                    ),
                    action = ImageCardAction.ToggleFavorite(uiModel.postId),
                ),
            ),
            trailing = emptyList(),
        )
    }
}

@Composable
internal fun ImageCardActionsComposable(
    uiModel: ImageCardActionsUiModel,
    onAction: (ImageCardAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ActionIconGroup(items = uiModel.leading, onAction = onAction)
        ActionIconGroup(items = uiModel.trailing, onAction = onAction)
    }
}

@Composable
private fun ActionIconGroup(
    items: List<ActionIconUiModel>,
    onAction: (ImageCardAction) -> Unit,
) {
    Row {
        items.forEach { actionIcon ->
            IconButton(onClick = { onAction(actionIcon.action) }) {
                IconComposable(uiModel = actionIcon.icon)
            }
        }
    }
}

@Preview
@Composable
private fun ImageCardActionsComposablePreview() {
    PixabayTheme {
        ImageCardActionsComposable(
            uiModel = ImageCardActionsUiModel(
                leading = listOf(
                    ActionIconUiModel(
                        icon = IconUiModel(
                            icon = PixabayIcons.FavoriteOutlined,
                            contentDescription = null,
                        ),
                        action = ImageCardAction.ToggleFavorite(postId = "123"),
                    ),
                ),
                trailing = listOf(
                    ActionIconUiModel(
                        icon = IconUiModel(
                            icon = PixabayIcons.FavoriteFilled,
                            contentDescription = null,
                        ),
                        action = ImageCardAction.ToggleFavorite(postId = "456"),
                    ),
                ),
            ),
            onAction = {},
        )
    }
}
