package module.expansion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExpansionView() {
    Box (modifier = Modifier.fillMaxSize().wrapContentSize()){
        Text("拓展....")
    }
}