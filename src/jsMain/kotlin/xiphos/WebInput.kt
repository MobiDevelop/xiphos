package xiphos

import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.TouchEvent
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import kotlin.browser.document
import kotlin.browser.window

class WebInput(private val canvas: HTMLCanvasElement,
               private val config: WebAppConfig
) : Input {

    init {
        hookEvents()
    }

    private val touch = BooleanArray(20)
    private val touchX = DoubleArray(20)
    private val touchY = DoubleArray(20)

    private val pressedKeys = mutableSetOf<Int>()

    private var inputProcessor: InputProcessor? = null

    private fun hookEvents() {
        document.addEventListener("keydown", { event -> val keyboardEvent = event as KeyboardEvent
            pressedKeys.add(keyboardEvent.keyCode)
            inputProcessor?.onKeyDown(keyboardEvent.keyCode)
        })
        document.addEventListener("keyup", { event -> val keyboardEvent = event as KeyboardEvent
            pressedKeys.remove(keyboardEvent.keyCode)
            inputProcessor?.onKeyUp(keyboardEvent.keyCode)
        })
        document.addEventListener("keypress", { event -> val keyboardEvent = event as KeyboardEvent
            inputProcessor?.onKeyTyped(keyboardEvent.charCode.toChar())
        })

        canvas.addEventListener("mousedown", { event -> val mouseEvent = event as MouseEvent
            touch[0] = true
            touchX[0] = mouseEvent.offsetX
            touchY[0] = mouseEvent.offsetY
            inputProcessor?.onTouchDown(mouseEvent.offsetX.toInt(), mouseEvent.offsetY.toInt())
        })
        canvas.addEventListener("mousemove", { event -> val mouseEvent = event as MouseEvent
            touchX[0] = mouseEvent.offsetX
            touchY[0] = mouseEvent.offsetY
            if (touch[0]) {
                inputProcessor?.onTouchDragged(mouseEvent.offsetX.toInt(), mouseEvent.offsetY.toInt())
            } else {
                inputProcessor?.onMouseMoved(mouseEvent.offsetX.toInt(), mouseEvent.offsetY.toInt())
            }
        })
        canvas.addEventListener("mouseup", { event -> val mouseEvent = event as MouseEvent
            touch[0] = false
            inputProcessor?.onTouchUp(mouseEvent.x.toInt(), mouseEvent.y.toInt())
        })
        canvas.addEventListener("wheel", { event -> val wheelEvent = event as WheelEvent
        })

        canvas.addEventListener("touchstart", { event -> val touchEvent = event as TouchEvent
        })
        canvas.addEventListener("touchmove", { event -> val touchEvent = event as TouchEvent

        })
        canvas.addEventListener("touchend", { event -> val touchEvent = event as TouchEvent

        })
        canvas.addEventListener("touchcancel", { event -> val touchEvent = event as TouchEvent

        })

        window.addEventListener("focus", { println("focus") })
        window.addEventListener("blur", { println("blur") })
    }

    override fun setInputProcessor(inputProcessor: InputProcessor?) {
        this.inputProcessor = inputProcessor
    }

}