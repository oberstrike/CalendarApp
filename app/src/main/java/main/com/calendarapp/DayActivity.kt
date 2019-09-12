package main.com.calendarapp

import android.os.Bundle
import android.text.Editable
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_day.*
import kotlinx.android.synthetic.main.content_day.*

class DayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)
        setSupportActionBar(toolbar)

        val intArray: IntArray =  this.intent.getIntArrayExtra("Date")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val text = "${ if(intArray[2] < 10) "0" + intArray[2] else intArray[2]}" +
                ":${ if(intArray[1] < 10) "0" + intArray[1] else intArray[1]}" +
                ":${ if(intArray[0] < 10) "0" + intArray[0] else intArray[0]}"

        dateView.text = text

    }

}
