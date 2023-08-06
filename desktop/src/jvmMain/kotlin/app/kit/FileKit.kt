package app.kit

import app.constant.PathInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.BrowseModel
import java.io.File

object FileKit {
    fun writeBrowseInfo(browseModel: BrowseModel) {
        val list = readBrowseInfo()
        list.apply {
            removeIf {
                it.url == browseModel.url
            }
            add(browseModel)
        }

        File(PathInfo.browseInfo).apply {
            if (!exists()) {
                parentFile?.mkdirs()
                createNewFile()
            }
            writeText(Json.encodeToString(list))
        }
    }

    fun readBrowseInfo(): MutableList<BrowseModel> {
        return try {
            val jsonString = File(PathInfo.browseInfo).readText()
            Json.decodeFromString<MutableList<BrowseModel>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            mutableListOf()
        }
    }
}