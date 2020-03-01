package xiphos.graphics

open class Color(
    open val r: Float,
    open val g: Float,
    open val b: Float,
    open val a: Float
) {

    constructor(color: Color): this(color.r, color. g, color.b, color.a)

    override fun toString(): String {
        return "Color(r=$r, g=$g, b=$b, a=$a)"
    }

    companion object {
        val WHITE = Color(1f, 1f, 1f, 1f)
        val BLACK = Color(0f, 0f, 0f, 1f)
        val RED = Color(1f, 0f, 0f, 1f)
        val GREEN = Color(0f, 1f, 0f, 1f)
        val BLUE = Color(0f, 0f, 1f, 1f)
    }

}

class MutableColor(
    override var r: Float,
    override var g: Float,
    override var b: Float,
    override var a: Float
) : Color(r, g, b, a) {

    constructor(color: Color): this(color.r, color. g, color.b, color.a)

    fun set(r: Float, g: Float, b: Float, a: Float): MutableColor {
        this.r = r
        this.g = g
        this.b = b
        this.a = a
        return this
    }

    fun set(color: Color): MutableColor {
        this.r = color.r
        this.g = color.g
        this.b = color.b
        this.a = color.a
        return this
    }

    override fun toString(): String {
        return "MutableColor(r=$r, g=$g, b=$b, a=$a)"
    }

}

fun Color.toMutableColor() = MutableColor(this)
fun MutableColor.toColor() = Color(this)


