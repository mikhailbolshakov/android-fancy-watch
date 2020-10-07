package ru.mikhailb.fancywatch.timer

import java.util.*

fun interface OnTimer {
    fun onTimer()
}

class WatchTimer(private val onTimerCallback: OnTimer) {

    private var task: WatchTimerTask? = null

    class WatchTimerTask(private val onTimerCallback: OnTimer) : TimerTask() {
        override fun run() {
            onTimerCallback.onTimer()
        }
    }

    fun run(period: Long = 1000): TimerTask {
        task = WatchTimerTask(onTimerCallback)
        Timer().schedule(task, 0, period)
        return task!!
    }

    fun stop() {
        task?.cancel()
        task = null
    }

}