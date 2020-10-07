package ru.mikhailb.fancywatch.face

import android.graphics.Canvas
import android.graphics.Paint

data class Point(val x: Float, val y: Float)

data class Line(val start: Point, val end: Point)

private fun degreeToRadian(deg: Float): Float {
    return deg * Math.PI.toFloat() / 180.0f;
}

/**
 * creates a new Line by start point, angle in degrees and long
 */
fun line(start: Point, angle: Float, long: Float): Line {
    return Line(start, calcEndPoint(start, angle, long))
}

fun calcEndPoint(start: Point, angle: Float, long: Float): Point {

    return Point(long * Math.sin(degreeToRadian(angle).toDouble()).toFloat(),
        long * Math.cos(degreeToRadian(angle).toDouble()).toFloat())

}

fun drawLine(canvas: Canvas, paint: Paint, line: Line) {
    canvas.drawLine(line.start.x, -line.start.y, line.end.x, -line.end.y, paint)
}