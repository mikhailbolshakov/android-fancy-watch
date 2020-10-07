package ru.mikhailb.fancywatch.watch

import java.util.*


data class Time(val hour: Int, val minute: Int, val second: Int)

class Watch {

    var timeZone: TimeZone = TimeZone.getDefault()

    fun getTime(): Time {
        val calendarInstance = Calendar.getInstance()
        return Time(
            calendarInstance.get(Calendar.HOUR),
            calendarInstance.get(Calendar.MINUTE),
            calendarInstance.get(Calendar.SECOND)
        )
    }

}