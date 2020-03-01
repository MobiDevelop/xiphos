package xiphos.math

class Matrix3 {

    companion object {
        const val M00 = 0
        const val M01 = 3
        const val M02 = 6
        const val M10 = 1
        const val M11 = 4
        const val M12 = 7
        const val M20 = 2
        const val M21 = 5
        const val M22 = 8
    }

    constructor() {
        setToIdentity()
    }

    constructor(values: FloatArray) {
        set(values)
    }

    val values = FloatArray(9)
    private val tempValues = FloatArray(9)

    fun setToIdentity(): Matrix3 {
        values[M00] = 1f
        values[M10] = 0f
        values[M20] = 0f
        values[M01] = 0f
        values[M11] = 1f
        values[M21] = 0f
        values[M02] = 0f
        values[M12] = 0f
        values[M22] = 1f
        return this
    }

    fun setToRotation(degrees: Degrees): Matrix3 {
        return setToRotationRad(degrees.toRadians())
    }

    fun setToRotationRad(radians: Radians): Matrix3 {
        val cos = kotlin.math.cos(radians)
        val sin = kotlin.math.sin(radians)

        values[M00] = cos;
        values[M10] = sin;
        values[M20] = 0f

        values[M01] = -sin
        values[M11] = cos
        values[M21] = 0f

        values[M02] = 0f
        values[M12] = 0f
        values[M22] = 1f

        return this
    }

    fun setToRotation(axis: Vector3, degrees: Degrees): Matrix3 {
        return setToRotationRad(axis, degrees.toRadians())
    }

    fun setToRotationRad(axis: Vector3, radians: Radians): Matrix3 {
        val cos = kotlin.math.cos(radians)
        val sin = kotlin.math.sin(radians)
        val oc = 1.0f - cos

        values[M00] = oc * axis.x * axis.x + cos
        values[M10] = oc * axis.x * axis.y - axis.z * sin
        values[M20] = oc * axis.z * axis.x + axis.y * sin
        values[M01] = oc * axis.x * axis.y + axis.z * sin
        values[M11] = oc * axis.y * axis.y + cos
        values[M21] = oc * axis.y * axis.z - axis.x * sin
        values[M02] = oc * axis.z * axis.x - axis.y * sin
        values[M12] = oc * axis.y * axis.z + axis.x * sin
        values[M22] = oc * axis.z * axis.z + cos

        return this
    }

    fun setToTranslation(x: Float, y: Float): Matrix3 {
        values[M00] = 1f
        values[M10] = 0f
        values[M20] = 0f

        values[M01] = 0f
        values[M11] = 1f
        values[M21] = 0f

        values[M02] = x
        values[M12] = y
        values[M22] = 1f
        return this
    }

    fun setToTranslation(translation: Vector2): Matrix3 {
        values[M00] = 1f
        values[M10] = 0f
        values[M20] = 0f

        values[M01] = 0f
        values[M11] = 1f
        values[M21] = 0f

        values[M02] = translation.x
        values[M12] = translation.y
        values[M22] = 1f
        return this
    }

    fun setToScaling(scaleX: Float, scaleY: Float): Matrix3 {
        values[M00] = scaleX
        values[M10] = 0f
        values[M20] = 0f
        values[M01] = 0f
        values[M11] = scaleY
        values[M21] = 0f
        values[M02] = 0f
        values[M12] = 0f
        values[M22] = 1f
        return this
    }

    fun setToScaling(scale: Vector2): Matrix3 {
        values[M00] = scale.x
        values[M10] = 0f
        values[M20] = 0f
        values[M01] = 0f
        values[M11] = scale.y
        values[M21] = 0f
        values[M02] = 0f
        values[M12] = 0f
        values[M22] = 1f
        return this
    }

    fun set(values: FloatArray): Matrix3 {
        values.copyInto(this.values)
        return this
    }

    fun set(other: Matrix3): Matrix3 {
        other.values.copyInto(values)
        return this
    }

    fun set(mat: Matrix4): Matrix3 {
        values[M00] = mat.values[Matrix4.M00]
        values[M10] = mat.values[Matrix4.M10]
        values[M20] = mat.values[Matrix4.M20]
        values[M01] = mat.values[Matrix4.M01]
        values[M11] = mat.values[Matrix4.M11]
        values[M21] = mat.values[Matrix4.M21]
        values[M02] = mat.values[Matrix4.M02]
        values[M12] = mat.values[Matrix4.M12]
        values[M22] = mat.values[Matrix4.M22]
        return this
    }


    fun rotate(degrees: Degrees): Matrix3 {
        return this
    }

    fun rotateRad(radians: Radians): Matrix3 {
        if (radians != 0f) {
            val cos = kotlin.math.cos(radians)
            val sin = kotlin.math.sin(radians)

            tempValues[M00] = cos
            tempValues[M10] = sin
            tempValues[M20] = 0f

            tempValues[M01] = -sin
            tempValues[M11] = cos
            tempValues[M21] = 0f

            tempValues[M02] = 0f
            tempValues[M12] = 0f
            tempValues[M22] = 1f

            mul(values, tempValues)
        }
        return this
    }

    fun translate(x: Float, y: Float): Matrix3 {
        values[M02] += x
        values[M12] += y
        return this
    }

    fun translate(vector: Vector2): Matrix3 {
        values[M02] += vector.x
        values[M12] += vector.y
        return this
    }

    fun translate(vector: Vector3): Matrix3 {
        values[M02] += vector.x
        values[M12] += vector.y
        return this
    }

    fun scale(): Matrix3 {
        return this
    }

    fun getTranslation(translation: Vector2): Vector2 {
        translation.x = values[M02]
        translation.y = values[M12]
        return translation
    }

    fun getScale(scale: Vector2): Vector2 {
        scale.x = values[M02]
        scale.y = values[M12]
        return scale
    }

    fun getRotation(): Degrees {
        return getRotationRad().toDegrees()
    }

    fun getRotationRad(): Radians {
        return kotlin.math.atan2(values[M10], values[M00])
    }

    fun postMultiply(m: Matrix3): Matrix3 {
        val v00 = values[M00] * m.values[M00] + values[M01] * m.values[M10] + values[M02] * m.values[M20]
        val v01 = values[M00] * m.values[M01] + values[M01] * m.values[M11] + values[M02] * m.values[M21]
        val v02 = values[M00] * m.values[M02] + values[M01] * m.values[M12] + values[M02] * m.values[M22]

        val v10 = values[M10] * m.values[M00] + values[M11] * m.values[M10] + values[M12] * m.values[M20]
        val v11 = values[M10] * m.values[M01] + values[M11] * m.values[M11] + values[M12] * m.values[M21]
        val v12 = values[M10] * m.values[M02] + values[M11] * m.values[M12] + values[M12] * m.values[M22]

        val v20 = values[M20] * m.values[M00] + values[M21] * m.values[M10] + values[M22] * m.values[M20]
        val v21 = values[M20] * m.values[M01] + values[M21] * m.values[M11] + values[M22] * m.values[M21]
        val v22 = values[M20] * m.values[M02] + values[M21] * m.values[M12] + values[M22] * m.values[M22]

        values[M00] = v00;
        values[M10] = v10;
        values[M20] = v20;
        values[M01] = v01;
        values[M11] = v11;
        values[M21] = v21;
        values[M02] = v02;
        values[M12] = v12;
        values[M22] = v22;

        return this
    }

    fun preMultiply(m: Matrix3): Matrix3 {
        val v00 = m.values[M00] * values[M00] + m.values[M01] * values[M10] + m.values[M02] * values[M20]
        val v01 = m.values[M00] * values[M01] + m.values[M01] * values[M11] + m.values[M02] * values[M21]
        val v02 = m.values[M00] * values[M02] + m.values[M01] * values[M12] + m.values[M02] * values[M22]

        val v10 = m.values[M10] * values[M00] + m.values[M11] * values[M10] + m.values[M12] * values[M20]
        val v11 = m.values[M10] * values[M01] + m.values[M11] * values[M11] + m.values[M12] * values[M21]
        val v12 = m.values[M10] * values[M02] + m.values[M11] * values[M12] + m.values[M12] * values[M22]

        val v20 = m.values[M20] * values[M00] + m.values[M21] * values[M10] + m.values[M22] * values[M20]
        val v21 = m.values[M20] * values[M01] + m.values[M21] * values[M11] + m.values[M22] * values[M21]
        val v22 = m.values[M20] * values[M02] + m.values[M21] * values[M12] + m.values[M22] * values[M22]

        values[M00] = v00;
        values[M10] = v10;
        values[M20] = v20;
        values[M01] = v01;
        values[M11] = v11;
        values[M21] = v21;
        values[M02] = v02;
        values[M12] = v12;
        values[M22] = v22;

        return this
    }

    fun determinant(): Float {
        val det = values[M00] * values[M11] * values[M22]
                + values[M01] * values[M12] * values[M20]
                + values[M02] * values[M10] * values[M21]
                - values[M00] * values[M12] * values[M21]
                - values[M01] * values[M10] * values[M22]
                - values[M02] * values[M11] * values[M20]
        return det
    }

    fun invert(): Matrix3 {
        val determinant = determinant()

        if (determinant == 0f) {
            throw IllegalStateException("Cannot invert a singular matrix")
        }

        val inverseDeterminant = 1.0f / determinant;

        tempValues[M00] = values[M11] * values[M22] - values[M21] * values[M12]
        tempValues[M10] = values[M20] * values[M12] - values[M10] * values[M22]
        tempValues[M20] = values[M10] * values[M21] - values[M20] * values[M11]
        tempValues[M01] = values[M21] * values[M02] - values[M01] * values[M22]
        tempValues[M11] = values[M00] * values[M22] - values[M20] * values[M02]
        tempValues[M21] = values[M20] * values[M01] - values[M00] * values[M21]
        tempValues[M02] = values[M01] * values[M12] - values[M11] * values[M02]
        tempValues[M12] = values[M10] * values[M02] - values[M00] * values[M12]
        tempValues[M22] = values[M00] * values[M11] - values[M10] * values[M01]

        values[M00] = inverseDeterminant * tempValues[M00]
        values[M10] = inverseDeterminant * tempValues[M10]
        values[M20] = inverseDeterminant * tempValues[M20]
        values[M01] = inverseDeterminant * tempValues[M01]
        values[M11] = inverseDeterminant * tempValues[M11]
        values[M21] = inverseDeterminant * tempValues[M21]
        values[M02] = inverseDeterminant * tempValues[M02]
        values[M12] = inverseDeterminant * tempValues[M12]
        values[M22] = inverseDeterminant * tempValues[M22]

        return this
    }

    override fun toString(): String {
        return "Matrix3(values=${values.contentToString()})"
    }

    private fun mul (mata: FloatArray, matb: FloatArray) {
        val v00 = mata[M00] * matb[M00] + mata[M01] * matb[M10] + mata[M02] * matb[M20];
        val v01 = mata[M00] * matb[M01] + mata[M01] * matb[M11] + mata[M02] * matb[M21];
        val v02 = mata[M00] * matb[M02] + mata[M01] * matb[M12] + mata[M02] * matb[M22];

        val v10 = mata[M10] * matb[M00] + mata[M11] * matb[M10] + mata[M12] * matb[M20];
        val v11 = mata[M10] * matb[M01] + mata[M11] * matb[M11] + mata[M12] * matb[M21];
        val v12 = mata[M10] * matb[M02] + mata[M11] * matb[M12] + mata[M12] * matb[M22];

        val v20 = mata[M20] * matb[M00] + mata[M21] * matb[M10] + mata[M22] * matb[M20];
        val v21 = mata[M20] * matb[M01] + mata[M21] * matb[M11] + mata[M22] * matb[M21];
        val v22 = mata[M20] * matb[M02] + mata[M21] * matb[M12] + mata[M22] * matb[M22];

        mata[M00] = v00;
        mata[M10] = v10;
        mata[M20] = v20;
        mata[M01] = v01;
        mata[M11] = v11;
        mata[M21] = v21;
        mata[M02] = v02;
        mata[M12] = v12;
        mata[M22] = v22;
    }

}