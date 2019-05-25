package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return this.toNum() - other.toNum()
    }

    fun toNum() : Int = dayOfMonth + month * 100 + year * 10000

    //operator fun plus(interval: TimeInterval) : MyDate {
    //}
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object: Iterator<MyDate> {
            var current: MyDate = start

            override fun hasNext(): Boolean {
                return current <= endInclusive
            }

            override fun next(): MyDate {
                val next = current
                current = current.nextDay()
                return next
            }
        }
    }

    operator fun contains(d: MyDate) : Boolean {
        return d >= start && d <= endInclusive
    }
}
