package main.com.calendarapp.views.exercise

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_exercise.*
import main.com.calendarapp.R
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.util.ActivenessContext
import main.com.calendarapp.util.ExerciseContext
import main.com.calendarapp.views.exercise.fragments.TrainingWithWeightsRecyclerViewFragment
import main.com.calendarapp.views.exercise.fragments.interfaces.OnTextChangeListener
import org.koin.android.viewmodel.ext.android.viewModel

class ExerciseActivity : AppCompatActivity(),
    OnTextChangeListener {

    private val myViewModel: ExerciseViewModel by viewModel()

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar)
        initBtn()
        myViewModel.init()
    }

    private fun initBtn() {
        btn_add_workout.setOnClickListener {


        }
    }

    override fun onDestroy() {
        btn_add_workout.setOnClickListener(null)
        super.onDestroy()
    }

    private fun addWorkoutSet() {

    }

    override fun onStart() {
        super.onStart()

        myViewModel.launch {
            ExerciseContext.workoutSets.subscribeOn(myViewModel.schedulerProvider.computation())
                .observeOn(myViewModel.schedulerProvider.ui()).subscribe {
                    for (i in it.indices) {
                        val workoutSet = it[i]
                        val fragment = TrainingWithWeightsRecyclerViewFragment(workoutSet, i + 1, this)
                        fragment.arguments = intent.extras
                        supportFragmentManager.beginTransaction()
                            .add(container_1.id + i, fragment).commit()
                        Log.i("Info", supportFragmentManager.backStackEntryCount.toString())

                    }

            }

        }
    }


    override fun onChange(workoutSet: WorkoutSet) {
        myViewModel.saveWorkoutSet(workoutSet)
    }

    override fun onPause() {
        myViewModel.onCleared()
        super.onPause()
    }


}
