package xiphos.math

class Matrix4 {

    companion object {
        const val M00 = 0
        const val M01 = 4
        const val M02 = 8
        const val M03 = 12
        const val M10 = 1
        const val M11 = 5
        const val M12 = 9
        const val M13 = 13
        const val M20 = 2
        const val M21 = 6
        const val M22 = 10
        const val M23 = 14
        const val M30 = 3
        const val M31 = 7
        const val M32 = 11
        const val M33 = 15
    }

    val values = FloatArray(16)

    fun setToIdentity() {
        values[M00] = 1f
        values[M10] = 0f
        values[M20] = 0f
        values[M30] = 0f
        values[M01] = 0f
        values[M11] = 1f
        values[M21] = 0f
        values[M31] = 0f
        values[M02] = 0f
        values[M12] = 0f
        values[M22] = 1f
        values[M32] = 0f
        values[M03] = 0f
        values[M13] = 0f
        values[M23] = 0f
        values[M33] = 1f
    }

}