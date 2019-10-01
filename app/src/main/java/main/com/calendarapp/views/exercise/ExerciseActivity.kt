package main.com.calendarapp.views.exercise

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.content_exercise.*
import main.com.calendarapp.R
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.views.exercise.fragments.WorkoutSetRecyclerViewAdapter

class ExerciseActivity : AppCompatActivity(), WorkoutSetRecyclerViewAdapter.OnClickListener{

    lateinit var workoutSetRecyclerViewAdapter : WorkoutSetRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar)
        initRecyclerView()
    }

    override fun onItemClick(position: Int) {

    }

    fun initRecyclerView() {
        workoutSetRecyclerViewAdapter = WorkoutSetRecyclerViewAdapter(this,this)
        workoutSetRecyclerViewAdapter.workouts = ArrayList( listOf(WorkoutSet(5,5,"Mein Name")))
        workoutRecyclerView.adapter = workoutSetRecyclerViewAdapter
    }

}
