package module.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SideNavigation(
    data: List<String> = listOf(),
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Row {
            LazyColumn(modifier = Modifier.fillMaxHeight().padding(horizontal = 10.dp)) {
                items(data.size) { index ->
                    val item = data[index]
                    Box(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Gray.copy(alpha = 0.2f))
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(item)
                    }
                }
            }

            Box(
                modifier = Modifier.width(1.dp).fillMaxHeight()
                    .background(color = Color.Gray.copy(alpha = 0.2f))
            )

            content()
        }
    }
}