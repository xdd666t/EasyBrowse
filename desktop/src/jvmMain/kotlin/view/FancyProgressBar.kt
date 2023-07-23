package view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FancyProgressBar(modifier: Modifier = Modifier, progress: Float = 0f) {
    val height = 15.dp
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.secondary,
            MaterialTheme.colors.primary
        )
    )

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(color = Color.LightGray, shape = RoundedCornerShape(height))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = progress)
                    .height(height)
                    .background(brush = gradient, shape = RoundedCornerShape(height))
            )
        }
    }
}