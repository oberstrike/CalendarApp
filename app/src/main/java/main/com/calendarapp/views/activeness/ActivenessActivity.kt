package main.com.calendarapp.views.activeness

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

import kotlinx.android.synthetic.main.activity_activeness.*
import kotlinx.android.synthetic.main.activity_activeness.view.*
import kotlinx.android.synthetic.main.content_activeness.*
import main.com.calendarapp.R
import main.com.calendarapp.data.local.FileManager
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ActivenessActivity : AppCompatActivity() {

    val myViewModel : ActivenessViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activeness)
        setSupportActionBar(toolbar)
        val name:String = intent.extras.get("Data") as String
        myViewModel.onStart(name)

        myViewModel.launch {
            myViewModel.subject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { value -> dataView.text = value }
        }


    }
}
