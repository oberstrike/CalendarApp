package main.com.calendarapp.util

object TextUtils {
    fun isEmpty(str: CharSequence?): Boolean {
        return str == null || str.length == 0
    }
}