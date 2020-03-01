package xiphos.math

open class Vector2(
    open var x: Float = 0.0f,
    open var y: Float = 0.0f
) : Vector<Vector2> {

    constructor(copy: Vector2) : this(copy.x, copy.y)

    companion object {
        val X = Vector2(1f, 0f)
        val Y = Vector2(0f, 1f)
    }

}

class MutableVector2(
    override var x: Float = 0.0f,
    override var y: Float = 0.0f
) : Vector2() {

    constructor(copy: Vector2) : this(copy.x, copy.y)

    fun set(x: Float, y: Float): MutableVector2 {
        this.x = x
        this.y = y
        return this
    }

    fun set(other: Vector2): MutableVector2 {
        this.x = other.x
        this.y = other.y
        return this
    }

}

fun Vector2.toMutableVector2() = MutableVector2(this)
fun MutableVector2.toVector2() = Vector2(this)