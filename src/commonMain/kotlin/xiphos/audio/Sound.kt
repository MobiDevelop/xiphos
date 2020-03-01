package xiphos.audio

interface Sound {

    fun play (volume: Float = 1.0f, pitch: Float = 1.0f, pan: Float = 0.0f)
    fun loop (volume: Float = 1.0f, pitch: Float = 1.0f, pan: Float = 0.0f)
    fun pause()
    fun resume()
    fun stop()

}