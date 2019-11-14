package main.com.fitnesstracker.views.activeness.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import main.com.fitnesstracker.R
import main.com.fitnesstracker.models.Exercise
import main.com.fitnesstracker.views.activeness.ActivenessViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RenameExerciseDialog(val exercise: Exercise) : AppCompatDialogFragment() {

    lateinit var newNameEdit: EditText

    private val myViewModel: ActivenessViewModel by sharedViewModel()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.AlertDialogCustom)

        val inflater = activity?.layoutInflater

        if (inflater != null) {
            val view = inflater.inflate(R.layout.fragment_rename_dialog, null)
            newNameEdit = view.findViewById(R.id.newNameEditText)

            with(builder) {
                setView(view)
                setPositiveButton("Ok") { dialog, _ ->
                    val newName = newNameEdit.text.toString()
                    if (newName.isNotEmpty() && newName.length > 6) {
                        myViewModel.renameExercise(exercise, newName)
                    }
                }
            }
        }


        return builder.create()
    }
}