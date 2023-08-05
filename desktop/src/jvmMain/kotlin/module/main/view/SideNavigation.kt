package module.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import module.main.MainViewModel
import view.AsyncImage
import view.loadImageBitmap

@Composable
fun SideNavigation(
    data: MainViewModel,
    onBrowseItem: (index: Int) -> Unit,
    onFunction: (index: Int) -> Unit,
    content: @Composable () -> Unit,
) {
    BuildBg(
        column = {
            // 浏览区域
            BrowseItems(
                data = data, onBrowseItem = onBrowseItem,
                modifier = Modifier.weight(weight = 1f),
            )

            // 分组
            GroupItems(data = data)

            // 设置, 添加
            FunctionItems(data = data) {
                onFunction(it)
            }
        },
        row = {
            // 分割线
            val modifier = Modifier.width(1.dp).fillMaxHeight()
            Box(modifier = modifier.background(color = Color.Gray.copy(alpha = 0.1f)))

            // web区域
            content()
        }
    )
}

@Composable
private fun BuildBg(
    column: @Composable ColumnScope.() -> Unit,
    row: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Row {
            Column(
                modifier = Modifier.width(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = column,
            )

            row()
        }
    }
}

@Composable
private fun BrowseItems(
    data: MainViewModel,
    modifier: Modifier = Modifier,
    onBrowseItem: (index: Int) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        val sideItems = data.sideItems
        items(sideItems.size) { index ->
            val item = sideItems[index]
            Box(
                modifier = Modifier.padding(top = 10.dp).size(40.dp).clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.2f)).clickable {
                        onBrowseItem(index)
                    }, contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    load = { loadImageBitmap(item.iconUrl) },
                    painterFor = { BitmapPainter(it) },
                    contentDescription = "Sample",
                    modifier = Modifier.padding(5.dp).fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun GroupItems(data: MainViewModel) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier.height(1.dp).fillMaxWidth().padding(horizontal = 10.dp)
                    .background(color = Color.Gray.copy(alpha = 0.2f))
            )
        }
        val groupItems = data.groupItems
        items(groupItems.size) { index ->
            val item = groupItems[index]
            Box(
                modifier = Modifier.padding(top = 7.dp).size(width = 40.dp, height = 25.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = Color.Gray.copy(alpha = 0.1f))
                    .clickable {},
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = item.imageVector,
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize().padding(4.dp)
                        .rotate(if (groupItems.size == (index + 1)) 90f else 0f)
                )
            }
        }
    }
}

@Composable
private fun FunctionItems(data: MainViewModel, onFunction: (index: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier.height(1.dp).fillMaxWidth().padding(horizontal = 10.dp)
                    .background(color = Color.Gray.copy(alpha = 0.2f))
            )
        }
        val functionItems = data.functionItems
        items(functionItems.size) { index ->
            val item = functionItems[index]
            Box(
                modifier = Modifier.padding(top = 5.dp).size(width = 40.dp, height = 25.dp)
                    .clip(RoundedCornerShape(4.dp)).clickable {
                        onFunction(index)
                    }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.imageVector,
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize().padding(4.dp)
                )
            }
        }
    }
}