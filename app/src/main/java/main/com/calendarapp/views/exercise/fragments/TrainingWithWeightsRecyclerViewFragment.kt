package main.com.calendarapp.views.exercise.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import main.com.calendarapp.R
import main.com.calendarapp.ext.afterTextChanged
import main.com.calendarapp.models.ExerciseType
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.views.exercise.ExerciseViewModel
import main.com.calendarapp.views.exercise.fragments.interfaces.OnTextChangeListener
import okhttp3.internal.toLongOrDefault
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.w3c.dom.Text
import java.util.function.Consumer
import kotlin.reflect.KMutableProperty1


class TrainingWithWeightsRecyclerViewFragment(
    val workoutSet: WorkoutSet,
    val setCount: Int,
    val onTextChangeListener: OnTextChangeListener,
    val exerciseType: ExerciseType
) : Fragment(), OnTextChangeListener {

    val myViewModel: ExerciseViewModel by sharedViewModel()
    lateinit var firstAttribute: EditText
    lateinit var secondAttribute: EditText
    lateinit var setText: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = when (exerciseType) {
            ExerciseType.SELFWEIGHTWORKOUTSET -> inflater.inflate(
                R.layout.layout_workset_with_one_attribute,
                container,
                false
            )
            else -> inflater.inflate(R.layout.layout_workset_with_two_attributes, container, false)

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        firstAttribute = view.findViewById(R.id.firstAttributeEditText)
        secondAttribute = view.findViewById(R.id.secondAttributeEditText)
        setText = view.findViewById(R.id.textViewSet)

        setText.text = setCount.toString()



        initEditText(firstAttribute,
            WorkoutSet::repetitions) {
            Pair(
                it.repetitions.toString(),
                it.repetitions
            )
        }

        secondAttribute.hint = workoutSet.weight.toString()
        secondAttribute.afterTextChanged {
            if (it.isNotEmpty() && it.isNotBlank()) {
                workoutSet.weight = it.toLongOrDefault(workoutSet.weight)
                onChange(workoutSet)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun initEditText(
        editText: EditText,
        property: KMutableProperty1<WorkoutSet, Long>,
        function: (workout: WorkoutSet) -> Pair<String, Long>
    ) {
        val pair = function.invoke(workoutSet)
        editText.hint = pair.first
        editText.afterTextChanged {
            if (it.isNotEmpty() && it.isNotBlank()) {
                property.set(workoutSet, it.toLongOrDefault(pair.second))
                onChange(workoutSet)
            }
        }
    }


    override fun onChange(workoutSet: WorkoutSet) {
        onTextChangeListener.onChange(workoutSet)
    }


}

