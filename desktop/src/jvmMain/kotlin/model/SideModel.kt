package model

import app.web.Chromium
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class SideModel(
    var title: String = "",
    var url: String = "",
    var iconUrl: String = "",
    @Transient
    var chromium: Chromium? = null,
)