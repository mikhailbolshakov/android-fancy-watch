package ru.mikhailb.fancywatch.face

import android.content.res.Resources
import android.graphics.Canvas
import ru.mikhailb.fancywatch.watch.Time

data class WatchFaceContext (val canvas: Canvas, val resources: Resources, val radius: Float, val center: Point,
                             val time: Time)