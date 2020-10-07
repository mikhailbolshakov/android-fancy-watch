package ru.mikhailb.fancywatch.face.impl

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import ru.mikhailb.fancywatch.*
import ru.mikhailb.fancywatch.face.*
import kotlin.collections.HashMap

class SimpleWatchFace : WatchFace {

    private val watchElementPaints = HashMap<String, Paint>()

    init {
        watchElementPaints["face-circle"] = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 20f
        }
        watchElementPaints["small-mark"] = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 10f
        }
        watchElementPaints["solid-mark"] = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 20f
        }
        watchElementPaints["hours-text"] = Paint().apply {
            color = Color.BLACK
            textSize = 80f
            textAlign = Paint.Align.LEFT
        }
        watchElementPaints["second-arrow"] = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 10f
        }
        watchElementPaints["minute-arrow"] = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 15f
        }
        watchElementPaints["hour-arrow"] = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 35f
        }
        watchElementPaints["point-on-arrow"] = Paint().apply {
            color = Color.GRAY
            style = Paint.Style.FILL
        }

    }

    override fun draw(context: WatchFaceContext) {

        context.canvas.drawColor(Color.GRAY)

        drawFace(context)

        drawArrows(context)

    }

    private fun drawArrows(context: WatchFaceContext) {

        val secondAngle = context.time.second * 6f
        val minuteAngle = context.time.minute * 6f + context.time.second / 10
        val hourAngle = context.time.hour * 30f + context.time.minute / 2

        drawLine(context.canvas, watchElementPaints["minute-arrow"]!!, line(context.center, minuteAngle, context.radius * 0.7f ))
        drawLine(context.canvas, watchElementPaints["minute-arrow"]!!, line(context.center, minuteAngle + 180, context.radius * 0.08f ))

        drawLine(context.canvas, watchElementPaints["hour-arrow"]!!, line(context.center, hourAngle, context.radius * 0.5f))
        drawLine(context.canvas, watchElementPaints["hour-arrow"]!!, line(context.center, hourAngle + 180, context.radius * 0.08f))


        drawLine(context.canvas, watchElementPaints["second-arrow"]!!, line(context.center, secondAngle, context.radius * 0.85f))
        drawLine(context.canvas, watchElementPaints["second-arrow"]!!, line(context.center, secondAngle + 180, context.radius * 0.08f))

        context.canvas.drawCircle(context.center.x, context.center.y, 10f, watchElementPaints["point-on-arrow"]!!)

    }

    private fun drawFace(context: WatchFaceContext) {

        context.canvas.drawCircle(context.center.x, context.center.y, context.radius, watchElementPaints["face-circle"]!!)

        for (ang in 6..360 step 6) {
            drawMark(ang, context)
            drawHourText(ang, context)
        }
    }

    private fun drawMark(angle: Int, context: WatchFaceContext) {
        val s = calcEndPoint(context.center, angle.toFloat(), context.radius - 80)
        val e = calcEndPoint(context.center, angle.toFloat(), context.radius - 40)
        drawLine(context.canvas, watchElementPaints[if (angle % 30 == 0) "face-circle" else "small-mark"]!!, Line(s, e))
    }

    private fun drawHourText(angle: Int, context: WatchFaceContext) {
        if (angle % 30 == 0) {

            val number = (angle / 30).toString()

            val textPoint = calcEndPoint(context.center, angle.toFloat(), context.radius - 140)

            val r = Rect()
            val paintHoursText = watchElementPaints["hours-text"]!!
            paintHoursText.getTextBounds(number, 0, number.length, r)

            val x: Float = textPoint.x - r.width() / 2f - r.left
            val y: Float = -textPoint.y + r.height() / 2f - r.bottom

            context.canvas.drawText(number, x, y, paintHoursText)
        }
    }

}