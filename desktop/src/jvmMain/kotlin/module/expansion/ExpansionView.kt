package module.expansion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpansionView() {
    val viewModel = ExpansionViewModel()
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.TopStart)) {
        Column(modifier = Modifier.padding(30.dp)) {
            EditInfo(title = "title", text = viewModel.addInfo.title)
            EditInfo(title = "url", text = viewModel.addInfo.url)
            Button(onClick = {
                viewModel.onSave()
            }) { Text("Save") }
        }
    }
}

@Composable
private fun EditInfo(
    title: String = "",
    text: MutableState<String>,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 20.dp),
    ) {
        Text(title, modifier = Modifier.width(50.dp).padding(end = 20.dp))
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .width(500.dp)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(horizontal = 15.dp, vertical = 12.dp)
                .wrapContentSize(align = Alignment.CenterStart)
        ) {
            BasicTextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}