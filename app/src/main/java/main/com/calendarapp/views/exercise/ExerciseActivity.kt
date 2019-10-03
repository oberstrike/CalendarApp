package main.com.calendarapp.views.exercise

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_exercise.*

import main.com.calendarapp.R
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.views.exercise.fragments.CustomAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ExerciseActivity :  AppCompatActivity() {

    val myViewModel: ExerciseViewModel by viewModel()
    private lateinit var customAdapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar)



        val exerciseId = intent.getIntExtra("ExerciseId", 0)
        val activenessId = intent.getIntExtra("ActivenessId", 0)
        val setCount = intent.getIntExtra("Count", 0)


        myViewModel.init(activenessId, exerciseId, setCount)
    }

    private fun initSaveBtn(){
        exercise_btn_save.setOnClickListener{

        }
    }

    private fun initListView(){
        val list = myViewModel.exercise?.workoutSets

        if(list != null)
            customAdapter = CustomAdapter(this, R.layout.fragment_workout, list)
        else
            customAdapter = CustomAdapter(this, R.layout.fragment_workout, ArrayList())

        listView.adapter = customAdapter
    }


}
