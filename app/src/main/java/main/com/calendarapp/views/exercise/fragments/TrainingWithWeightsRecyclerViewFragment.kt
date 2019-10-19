package main.com.calendarapp.views.exercise.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import main.com.calendarapp.R
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.views.exercise.ExerciseViewModel
import main.com.calendarapp.views.exercise.fragments.interfaces.OnTextChangeListener
import org.koin.android.viewmodel.ext.android.sharedViewModel


class TrainingWithWeightsRecyclerViewFragment(
    val workoutSet: WorkoutSet,
    val setCount: Int,
    val onTextChangeListener: OnTextChangeListener
) : Fragment(), OnTextChangeListener {

    val myViewModel: ExerciseViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_workset_with_two_attributes, container, false)

        return view
    }


    override fun onChange(workoutSet: WorkoutSet) {
        onTextChangeListener.onChange(workoutSet)
    }

}

