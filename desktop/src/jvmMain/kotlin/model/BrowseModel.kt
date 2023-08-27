package model

import app.web.Chromium
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class BrowseModel(
    var title: String = "",
    var url: String = "",
    var iconPath: String = "",
    var selected: Boolean = false,
    @Transient
    var chromium: Chromium? = null,
)