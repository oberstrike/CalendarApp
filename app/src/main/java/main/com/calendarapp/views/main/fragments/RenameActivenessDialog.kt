package main.com.calendarapp.views.main.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import main.com.calendarapp.R
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.views.main.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RenameActivenessDialog(val activeness: Activeness) : AppCompatDialogFragment() {

    lateinit var newNameEdit: EditText

    private val myViewModel: MainViewModel by sharedViewModel()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.AlertDialogCustom)

        val inflater = activity?.layoutInflater

        if (inflater != null) {
            val view = inflater.inflate(R.layout.fragment_rename_dialog, null)
            newNameEdit = view.findViewById(R.id.newNameEditText)

            with(builder) {
                setView(view)
                setPositiveButton("Ok") { dialog, _ ->
                    if (newNameEdit.text.isNotEmpty()) {
                        val newName = newNameEdit.text.toString()
                        if (newName.length > 6) {
                            myViewModel.rename(activeness, newName)
                        }
                    }
                    dialog.cancel()
                }
            }
        }
        return builder.create()
    }
}