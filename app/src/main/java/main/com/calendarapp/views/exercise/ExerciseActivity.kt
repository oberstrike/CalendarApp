package main.com.calendarapp.views.exercise

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.pwittchen.swipe.library.rx2.Swipe
import com.github.pwittchen.swipe.library.rx2.SwipeEvent
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_exercise.*
import main.com.calendarapp.R
import main.com.calendarapp.models.ExerciseType
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.util.ActivenessContext
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
    private lateinit var workoutSetAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    private var started: Boolean = false
    private var swipe: Swipe? = null
    private var status = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar)
        myViewModel.init()
        initAddBtn()
        supportActionBar?.title = ActivenessContext.activeExercise.name

    }

    private fun initAddBtn() {
        btn_add_workout_set.setOnClickListener {
            started = false
            myViewModel.addWorkoutSet()
        }

    }

    override fun onStart() {
        super.onStart()

        if (myViewModel.type == ExerciseType.STRENGTHWORKOUTSET || myViewModel.type == ExerciseType.SELFWEIGHTWORKOUTSET) {
            status = if (myViewModel.type == ExerciseType.STRENGTHWORKOUTSET) 0 else 1
            swipe = Swipe(20, 100)

            myViewModel.launch {
                swipe!!.observe().subscribeOn(myViewModel.schedulerProvider.computation())
                    .observeOn(myViewModel.schedulerProvider.ui())
                    .subscribe {
                        started = false
                        if (it == SwipeEvent.SWIPED_RIGHT && status == 0) {
                            myViewModel.setExerciseType(ExerciseType.SELFWEIGHTWORKOUTSET)
                            status = 1
                        } else if (it == SwipeEvent.SWIPED_LEFT && status == 1) {
                            myViewModel.setExerciseType(ExerciseType.STRENGTHWORKOUTSET)
                            status = 0
                        }
                    }
            }
        }


        myViewModel.launch {
            ActivenessContext.activeExerciseObservable
                .subscribeOn(myViewModel.schedulerProvider.computation())
                .observeOn(myViewModel.schedulerProvider.ui()).subscribe {
                    val workoutSets = it.first().workoutSets

                    if (started)
                        return@subscribe

                    val obj: Any = when (myViewModel.type) {
                        ExerciseType.ENDURANCEWORKOUTSET -> EnduranceTrainingRecyclerViewAdapter(
                            this,
                            this
                        )
                        ExerciseType.SWIMWORKOUTSET -> SwimTrainingRecyclerViewAdapter(this, this)
                        ExerciseType.SELFWEIGHTWORKOUTSET -> TrainingWithoutWeightRecyclerViewAdapter(
                            this,
                            this
                        )
                        else -> TrainingWithWeightsRecyclerViewAdapter(this, this)
                    }

                    workoutSetAdapter = obj as RecyclerView.Adapter<RecyclerView.ViewHolder>
                    workoutSetRecyclerView.layoutManager = LinearLayoutManager(this)
                    workoutSetRecyclerView.adapter = workoutSetAdapter


                    val adapter = workoutSetAdapter as Fillable
                    adapter.setItems(ArrayList(workoutSets))
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

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (swipe != null)
            swipe?.dispatchTouchEvent(event)
        return super.dispatchTouchEvent(event)
    }




}
