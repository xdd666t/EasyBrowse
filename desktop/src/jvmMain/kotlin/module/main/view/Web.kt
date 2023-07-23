package module.main.view

import androidx.compose.runtime.Composable
import module.main.model.SideModel

@Composable
fun WebScope(sideItems: List<SideModel>, selectIndex: Int) {
    sideItems[selectIndex].chromium?.Web()
}