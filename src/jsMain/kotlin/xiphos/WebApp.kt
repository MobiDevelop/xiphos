package xiphos

import kotlin.browser.*

class WebApp(
    private val config: WebAppConfig,
    private val listener: AppListener
): App {

    private val graphics = WebGraphics(config)
    private val audio = WebAudio()
    private val input = WebInput(graphics.canvas, config)
    private val files = WebFiles()
    private val net = WebNet()

    init {
        listener.create()
        listener.resize(graphics.getWidth(), graphics.getHeight())

        window.requestAnimationFrame(::mainLoop)
    }

    override fun listener(): AppListener {
        return listener
    }

    override fun graphics(): Graphics {
        return graphics
    }

    override fun audio(): Audio {
        return audio
    }

    override fun input(): Input {
        return input
    }

    override fun files(): Files {
        return files
    }

    override fun net(): Net {
        return net
    }

    private fun mainLoop(timestamp: Double) {
        listener.render()
        window.requestAnimationFrame(::mainLoop)
    }

}