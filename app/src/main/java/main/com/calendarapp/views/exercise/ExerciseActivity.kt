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
import main.com.calendarapp.views.exercise.fragments.TrainingWithoutWeightRecyclerViewAdapter
import main.com.calendarapp.views.exercise.fragments.interfaces.Fillable
import main.com.calendarapp.views.exercise.fragments.interfaces.OnTextChangeListener
import org.koin.android.viewmodel.ext.android.viewModel

class ExerciseActivity : AppCompatActivity(),
    OnTextChangeListener {

    private val myViewModel: ExerciseViewModel by viewModel()

    private lateinit var recyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar)
        val setCount = intent.getIntExtra("Count", 0)
        myViewModel.init(setCount)
        initListView()
    }


    private fun initListView(){

        val obj: Any = when (myViewModel.type) {
            ExerciseType.ENDURANCEWORKOUTSET -> EnduranceTrainingRecyclerViewAdapter(this, this)
            ExerciseType.STRENGTHWORKOUTSET -> TrainingWithWeightsRecyclerViewAdapter(this, this)
            ExerciseType.SWIMWORKOUTSET -> SwimTrainingRecyclerViewAdapter(this, this)
            ExerciseType.SELFWEIGHTWORKOUTSET -> TrainingWithoutWeightRecyclerViewAdapter(
                this,
                this
            )
        }

        recyclerViewAdapter = obj as RecyclerView.Adapter<RecyclerView.ViewHolder>
        workoutSetRecyclerView.adapter = recyclerViewAdapter
        workoutSetRecyclerView.layoutManager = LinearLayoutManager(this)


        myViewModel.launch {
            ExerciseContext.activeExerciseObservable
                .subscribeOn(myViewModel.schedulerProvider.computation())
                .observeOn(myViewModel.schedulerProvider.ui())
                .subscribe {
                    val exercise = it.first()
                    val workoutSet = exercise.workoutSets
                    val customRecyclerViewAdapter =
                        recyclerViewAdapter as Fillable
                    customRecyclerViewAdapter.items = ArrayList(workoutSet)
                    customRecyclerViewAdapter.notifyDataSetChanged()
                }
        }
    }


    override fun onChange(workoutSet: WorkoutSet) {
        myViewModel.saveWorkoutSet(workoutSet)
    }


}
