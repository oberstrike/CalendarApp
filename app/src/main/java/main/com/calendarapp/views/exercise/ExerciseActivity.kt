package main.com.calendarapp.views.exercise

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_exercise.*
import main.com.calendarapp.R
import main.com.calendarapp.util.ExerciseContext
import main.com.calendarapp.views.exercise.fragments.CustomListViewAdapter
import okhttp3.internal.toLongOrDefault
import org.koin.android.viewmodel.ext.android.viewModel

class ExerciseActivity :  AppCompatActivity() {

    private val myViewModel: ExerciseViewModel by viewModel()

    private lateinit var customListViewAdapter: CustomListViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar)
        val setCount = intent.getIntExtra("Count", 0)
        myViewModel.init(setCount)
        initListView()
        initSaveBtn()
    }

    //Gibt dem Speicher-Button seine Funktionalität
    private fun initSaveBtn(){
        exercise_btn_save.setOnClickListener{
            var counter = 0

            myViewModel.launch{
                ExerciseContext.activeExerciseObservable
                    .subscribeOn(myViewModel.schedulerProvider.computation())
                    .observeOn(myViewModel.schedulerProvider.computation())
                    .subscribe { each ->
                        if(counter != 0)
                            return@subscribe
                        counter += 1

                        val exercise = each[0]

                        for (i in exercise.workoutSets.indices) {
                            val child = listView.getChildAt(i)
                            val viewHolder = child.tag as CustomListViewAdapter.ViewHolder
                            val workoutSet = exercise.workoutSets[i]
                            val repetitions = viewHolder.setText?.text.toString()
                                .toLongOrDefault(workoutSet.repetitions)
                            val weight = viewHolder.weightText?.text.toString()
                                .toLongOrDefault(workoutSet.weight)
                            workoutSet.repetitions = repetitions
                            workoutSet.weight = weight
                            myViewModel.saveWorkoutSet(workoutSet)
                        }
                        myViewModel.saveExercise(exercise)

                        myViewModel.schedulerProvider.ui().createWorker().schedule{
                            Toast.makeText(this, "Gespeichert", Toast.LENGTH_LONG).show()
                        }

                    }
            }
        }
    }



    private fun initListView(){

        customListViewAdapter = CustomListViewAdapter(this, R.layout.fragment_workout, ArrayList())
        listView.adapter = customListViewAdapter


        myViewModel.launch {
            ExerciseContext.activeExerciseObservable
                .subscribeOn(myViewModel.schedulerProvider.computation())
                .observeOn(myViewModel.schedulerProvider.ui())
                .subscribe {
                    val workoutSet = it.first().workoutSets
                    customListViewAdapter.items = workoutSet
                    customListViewAdapter.notifyDataSetChanged()
                }
        }


    }


}
