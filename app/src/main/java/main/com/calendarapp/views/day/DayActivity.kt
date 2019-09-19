package main.com.calendarapp.views.day

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

import kotlinx.android.synthetic.main.activity_day.*
import kotlinx.android.synthetic.main.activity_day.view.*
import kotlinx.android.synthetic.main.content_day.*
import main.com.calendarapp.R
import org.koin.android.viewmodel.ext.android.viewModel

class DayActivity : AppCompatActivity() {

    val myViewModel : DayViewModel by viewModel<DayViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)
        setSupportActionBar(toolbar)

        val intArray: IntArray = this.intent.getIntArrayExtra("Date")

        val text = "${if (intArray[2] < 10) "0" + intArray[2] else intArray[2]}" +
                ".${if (intArray[1] < 10) "0" + intArray[1] else intArray[1]}" +
                ".${if (intArray[0] < 10) "0" + intArray[0] else intArray[0]}"

        dateView.text = text

        myViewModel.subject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { t -> dateView.text = t })


        button.setOnClickListener(View.OnClickListener {
            myViewModel.setName(editText.text.toString())
        })



    }
}
