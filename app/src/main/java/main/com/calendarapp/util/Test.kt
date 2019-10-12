package main.com.calendarapp.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlin.math.abs

fun getReversedIndex(oldIndex: Int, size: Int) = abs(oldIndex - size)

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}
