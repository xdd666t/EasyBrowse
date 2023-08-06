package model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class FunctionModel(
    var imageVector: ImageVector,
    var title: String = "",
    var page: (@Composable () -> Unit)? = null,
)