package app.kit

import app.constant.PathInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.SideModel
import java.io.File

object FileKit {
    fun writeBrowseInfo(sideModel: SideModel) {
        val list = readBrowseInfo()
        list.apply {
            removeIf {
                it.url == sideModel.url
            }
            add(sideModel)
        }

        File(PathInfo.browseInfo).apply {
            if (!exists()) {
                parentFile?.mkdirs()
                createNewFile()
            }
            writeText(Json.encodeToString(list))
        }
    }

    fun readBrowseInfo(): MutableList<SideModel> {
        return try {
            val jsonString = File(PathInfo.browseInfo).readText()
            Json.decodeFromString<MutableList<SideModel>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            mutableListOf()
        }
    }
}