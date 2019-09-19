package main.com.calendarapp.views.activeness

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

import kotlinx.android.synthetic.main.activity_activeness.*
import kotlinx.android.synthetic.main.content_activeness.*
import main.com.calendarapp.R
import org.koin.android.viewmodel.ext.android.viewModel

class ActivenessActivity : AppCompatActivity() {

    val myViewModel : ActivenessViewModel by viewModel<ActivenessViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activeness)
        setSupportActionBar(toolbar)

        val intArray: IntArray = this.intent.getIntArrayExtra("Date")


        myViewModel.subject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { t -> dateView.text = t })

        button.setOnClickListener(View.OnClickListener {
            myViewModel.setName(editText.text.toString())
        })



    }
}
