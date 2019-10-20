package main.com.calendarapp.views.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_exercise.*
import main.com.calendarapp.R
import main.com.calendarapp.models.ExerciseType
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.util.ExerciseContext
import main.com.calendarapp.views.exercise.fragments.EnduranceTrainingRecyclerViewAdapter
import main.com.calendarapp.views.exercise.fragments.SwimTrainingRecyclerViewAdapter
import main.com.calendarapp.views.exercise.fragments.TrainingWithWeightsRecyclerViewAdapter
import main.com.calendarapp.views.exercise.fragments.interfaces.Fillable
import main.com.calendarapp.views.exercise.fragments.interfaces.OnTextChangeListener
import org.koin.android.viewmodel.ext.android.viewModel

class ExerciseActivity : AppCompatActivity(),
    OnTextChangeListener {

    private val myViewModel: ExerciseViewModel by viewModel()
    private lateinit var workoutSetAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar)
        myViewModel.init()
    }

    override fun onStart() {
        super.onStart()


        val obj: Any = when (myViewModel.type) {
            ExerciseType.ENDURANCEWORKOUTSET -> EnduranceTrainingRecyclerViewAdapter(this, this)
            ExerciseType.SWIMWORKOUTSET -> SwimTrainingRecyclerViewAdapter(this, this)
            else -> TrainingWithWeightsRecyclerViewAdapter(this, this)
        }

        workoutSetAdapter = obj as RecyclerView.Adapter<RecyclerView.ViewHolder>
        workoutSetRecyclerView.layoutManager = LinearLayoutManager(this)
        workoutSetRecyclerView.adapter = workoutSetAdapter

        var started = false

        myViewModel.launch {
            ExerciseContext.workoutSets
                .subscribeOn(myViewModel.schedulerProvider.computation())
                .observeOn(myViewModel.schedulerProvider.ui()).subscribe {
                    if (started)
                        return@subscribe
                    val adapter = workoutSetAdapter as Fillable
                    adapter.setItems(ArrayList(it))
                    started = true
                }
        }
    }


    override fun onPause() {
        myViewModel.onCleared()
        super.onPause()
    }


    override fun onChange(workoutSet: WorkoutSet) {
        myViewModel.saveWorkoutSet(workoutSet)
    }



}
