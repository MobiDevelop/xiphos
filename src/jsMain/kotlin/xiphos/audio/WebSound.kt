package xiphos.audio

import org.w3c.dom.Audio

class WebSound(private val file: String) : Sound {

    val source = Audio(file)

    init {
        source.load()
    }

    override fun play(volume: Float, pitch: Float, pan: Float) {
        source.volume = volume.toDouble()
        source.playbackRate = pitch.toDouble()
        source.currentTime = 0.0
        source.play()
    }

    override fun loop(volume: Float, pitch: Float, pan: Float) {
        source.loop = true
        source.volume = volume.toDouble()
        source.playbackRate = pitch.toDouble()
        source.currentTime = 0.0
        source.play()
    }

    override fun pause() {
        source.pause()
    }

    override fun resume() {
        if (source.paused) {
            source.play()
        }
    }

    override fun stop() {
        source.pause()
        source.currentTime = 0.0
    }

}