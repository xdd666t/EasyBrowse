package module.main.model

import app.web.Chromium

data class SideModel(
    var title: String = "",
    var url: String = "",
    var iconUrl: String = "",
    var chromium: Chromium? = null,
)