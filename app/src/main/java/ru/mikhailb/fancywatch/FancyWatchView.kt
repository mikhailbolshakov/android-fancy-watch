package ru.mikhailb.fancywatch

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.view.View
import ru.mikhailb.fancywatch.face.Point
import ru.mikhailb.fancywatch.face.WatchFaceContext
import ru.mikhailb.fancywatch.face.impl.SimpleWatchFace
import ru.mikhailb.fancywatch.timer.WatchTimer
import ru.mikhailb.fancywatch.watch.Watch

class FancyWatchView(context: Context) : View(context) {

    private val timer: WatchTimer = WatchTimer { onTimeChanged() }
    private val watch = Watch()
    private val face = SimpleWatchFace()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        timer.run(500)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        timer.stop()
    }

    private fun onTimeChanged() {
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val center = Point( width / 2f,  height / 2f)
        canvas.translate(center.x, center.y)

        val radius = width.coerceAtMost(height) / 2f - 20

        val drawableCtx = WatchFaceContext(canvas, resources, radius, Point(0f, 0f), watch.getTime())
        face.draw(drawableCtx)

    }

}