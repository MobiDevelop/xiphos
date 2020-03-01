package xiphos

interface InputProcessor {

    fun onMouseMoved(screenX: Int, screenY: Int) {
        /* Nothing by default */
    }

    fun onTouchDown(screenX: Int, screenY: Int, pointer: Int = 0, button: Input.Button = Input.Button.PRIMARY) {
        /* Nothing by default */
    }

    fun onTouchDragged(screenX: Int, screenY: Int, pointer: Int = 0) {
        /* Nothing by default */
    }

    fun onTouchUp(screenX: Int, screenY: Int, pointer: Int = 0, button: Input.Button = Input.Button.PRIMARY) {
        /* Nothing by default */
    }

    fun onKeyDown(keyCode: Int) {
        /* Nothing by default */
    }

    fun onKeyUp(keyCode: Int) {
        /* Nothing by default */
    }

    fun onKeyTyped(character: Char) {
        /* Nothing by default */
    }

}