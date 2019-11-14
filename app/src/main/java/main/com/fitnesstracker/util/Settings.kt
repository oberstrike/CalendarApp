package main.com.fitnesstracker.util

data class Settings(var filterType: FilterType, var page: Int)


enum class FilterType {
    NAME, DATE, WEEKDAY
}
