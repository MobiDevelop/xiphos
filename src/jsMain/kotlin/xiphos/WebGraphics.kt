package xiphos

import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.random.Random

class WebGraphics(val config: WebAppConfig) : Graphics {

    val canvas: HTMLCanvasElement
    val context: WebGLRenderingContext

    init {
        canvas = document.createElement("canvas") as HTMLCanvasElement
        context = canvas.getContext("webgl") as WebGLRenderingContext

        canvas.width = config.width
        canvas.height = config.height
        document.body?.appendChild(canvas)
        canvas.focus()
        step(0.0)
    }

    override fun getWidth(): Int {
        return canvas.width
    }

    override fun getHeight(): Int {
        return canvas.height
    }

    fun step(timestamp: Double) {
        context.clearColor(
            Random.nextFloat(),
            Random.nextFloat(),
            Random.nextFloat(), 1.0f)
        context.clear(WebGLRenderingContext.COLOR_BUFFER_BIT)
    }


}