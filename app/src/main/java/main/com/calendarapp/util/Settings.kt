package main.com.calendarapp.util

data class Settings(var filterType: FilterType, var page: Int)


enum class FilterType {
    NAME, DATE, WEEKDAY
}
