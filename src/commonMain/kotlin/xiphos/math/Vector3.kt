package xiphos.math

open class Vector3(
    open val x: Float = 0.0f,
    open val y: Float = 0.0f,
    open val z: Float = 0.0f
) : Vector<Vector3> {

    constructor(vector3: Vector3): this(vector3.x, vector3.y, vector3.z)

    companion object {
        val X = Vector3(1f, 0f, 0f)
        val Y = Vector3(0f, 1f, 0f)
        val Z = Vector3(0f, 0f, 1f)
    }

}

class MutableVector3(
    override var x: Float = 0.0f,
    override var y: Float = 0.0f,
    override var z: Float = 0.0f
) : Vector3() {

    constructor(vector3: Vector3): this(vector3.x, vector3.y, vector3.z)

    fun set(x: Float, y: Float, z: Float): MutableVector3 {
        this.x = x
        this.y = y
        this.z = z
        return this
    }

    fun set(other: Vector3): MutableVector3 {
        this.x = other.x
        this.y = other.y
        this.z = other.z
        return this
    }

}

fun Vector3.toMutableVector3() = MutableVector3(this)
fun MutableVector3.toVector3() = Vector3(this)