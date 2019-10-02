package main.com.calendarapp.views.exercise

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

import main.com.calendarapp.R
import main.com.calendarapp.views.exercise.fragments.CustomAdapter

class ExerciseActivity :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar)

        val id = intent.getIntExtra("data", 0)

    }


}
