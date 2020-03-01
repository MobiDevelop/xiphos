package xiphos

interface AppListener {

    fun create() {
        /* Nothing by default */
    }

    fun render() {
        /* Nothing by default */
    }

    fun pause() {
        /* Nothing by default */
    }

    fun resume() {
        /* Nothing by default */
    }

    fun dispose() {
        /* Nothing by default */
    }

    fun resize(width: Int, height: Int) {
        /* Nothing by default */
    }

}