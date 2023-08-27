package view

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.unit.Density
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.net.URL

/**
 * 构建Painter，为了图片使用
 *
 * Image(
 *     painter = painterResource(getWeatherIcon(dailyBean.iconDay)), "",
 * )
 */
@Composable
fun <T> AsyncImage(
    load: suspend () -> T?,
    painterFor: @Composable (T) -> Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val image: T? by produceState<T?>(null) {
        value = withContext(Dispatchers.IO) {
            try {
                load()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
    if (image != null) {
        Image(
            painter = painterFor(image!!),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}


fun loadImageBitmap(url: String): ImageBitmap? {
    return try {
        return if (url.startsWith("http")) {
            // 加载网络图片
            URL(url).openStream().buffered().use(::loadImageBitmap)
        } else {
            // 加载本地图片
            File(url).inputStream().buffered().use(::loadImageBitmap)
        }
    } catch (e: Exception) {
        null
    }
}


fun loadSvgPainter(url: String, density: Density): Painter =
    URL(url).openStream().buffered().use { loadSvgPainter(it, density) }

