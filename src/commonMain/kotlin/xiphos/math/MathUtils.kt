package xiphos.math

const val PI = 3.1415927f

typealias Degrees = Float
typealias Radians = Float

fun Degrees.toRadians(): Radians = (this / 180 * PI)
fun Radians.toDegrees(): Degrees = (this * 180 / PI)