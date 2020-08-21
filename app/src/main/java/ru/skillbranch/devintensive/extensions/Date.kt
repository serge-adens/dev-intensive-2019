package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String {
            val (tens, ones) = Utils.getTensAndOnes(value)
            return when (ones) {
                1 -> if (tens == 1) "$value секунд" else "$value секунду"
                in 2..4 -> if (tens == 1) "$value секунд" else "$value секунды"
                else -> "$value секунд"
            }
        }
    },
    MINUTE {
        override fun plural(value: Int): String {
            val (tens, ones) = Utils.getTensAndOnes(value)
            return when (ones) {
                1 -> if (tens == 1) "$value минут" else "$value минуту"
                in 2..4 -> if (tens == 1) "$value минут" else "$value минуты"
                else -> "$value минут"
            }
        }
    },
    HOUR {
        override fun plural(value: Int): String {
            val (tens, ones) = Utils.getTensAndOnes(value)
            return when (ones) {
                1 -> if (tens == 1) "$value часов" else "$value час"
                in 2..4 -> if (tens == 1) "$value часов" else "$value часа"
                else -> "$value часов"
            }
        }
    },
    DAY {
        override fun plural(value: Int): String {
            val (tens, ones) = Utils.getTensAndOnes(value)
            return when (ones) {
                1 -> if (tens == 1) "$value дней" else "$value день"
                in 2..4 -> if (tens == 1) "$value дней" else "$value дня"
                else -> "$value дней"
            }
        }
    };

    abstract fun plural(value: Int): String
}


fun Date.humanizeDiff(date: Date = Date()): String {
    //TODO("Not yet implemented")
/*
0с - 1с "только что"
1с - 45с "несколько секунд назад"
45с - 75с "минуту назад"
75с - 45мин "N минут назад"
45мин - 75мин "час назад"
75мин 22ч "N часов назад"
22ч - 26ч "день назад"
26ч - 360д "N дней назад"
>360д "более года назад"
 */
    var diff = date.time - this.time
    val inpast = diff >= 0
    diff = abs(diff)
    return when {
        diff <= SECOND -> "только что"
        diff <= 45 * SECOND -> if (inpast) "несколько секунд назад" else "через несколько секунд"
        diff <= 75 * SECOND -> if (inpast) "минуту назад" else "через минуту"
        diff <= 45 * MINUTE -> {
            val minute = diff.div(MINUTE).toInt()
            if (inpast) "${TimeUnits.MINUTE.plural(minute)} назад" else "через ${TimeUnits.MINUTE.plural(minute)}"
        }
        diff <= 75 * MINUTE -> if (inpast) "час назад" else "через час"
        diff <= 22 * HOUR -> {
            val hour = diff.div(HOUR).toInt()
            if (inpast) "${TimeUnits.HOUR.plural(hour)} назад" else "через ${TimeUnits.HOUR.plural(hour)}"
        }
        diff <= 26 * HOUR -> if (inpast) "день назад" else "через день"
        diff <= 360 * DAY -> {
            val hour = diff.div(DAY).toInt()
            if (inpast) "${TimeUnits.DAY.plural(hour)} назад" else "через ${TimeUnits.DAY.plural(hour)}"
        }
        else -> if (inpast) "более года назад" else "более чем через год"
    }
}
