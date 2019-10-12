package main.com.calendarapp.views.activeness.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import main.com.calendarapp.R
import main.com.calendarapp.views.activeness.ActivenessViewModel
import main.com.calendarapp.views.exercise.ExerciseActivity
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddNewExerciseDialog : AppCompatDialogFragment() {

    lateinit var editText: EditText

    private val myViewModel: ActivenessViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity, R.style.AlertDialogCustom)

        val inflater = activity?.layoutInflater

        if (inflater != null) {
            val view = inflater.inflate(R.layout.fragment_new_exercise_dialog, null)

            builder.setView(view)
            builder.setPositiveButton("Ok") { dialog, _ ->
                onPositiveButtonClick(dialog, editText.text.toString())
                dialog.cancel()
            }
            editText = view.findViewById(R.id.setCountEditText)
        }
        return builder.create()
    }

    private fun onPositiveButtonClick(dialog: DialogInterface, text: String) {
        val count = text.toIntOrNull()
        val max = 7
        if (count == null)
            Toast.makeText(context, "Falsche Eingabe", Toast.LENGTH_LONG).show()
        else {
            if (count < max) {
                myViewModel.addNewActiveExercise()
                val intent = Intent(context, ExerciseActivity::class.java)
                intent.putExtra("Count", count)
                startActivity(intent)
                dialog.cancel()
            } else {
                Toast.makeText(
                    context,
                    "Die maximale Anzahl an Sets beträgt $max",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


}