package main.com.calendarapp.util

data class Settings(var filterType: FilterType)


enum class FilterType {
    NAME, DATE, WEEKDAY
}
