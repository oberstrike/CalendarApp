package main.com.fitnesstracker.views.main.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatDialogFragment
import main.com.fitnesstracker.R
import main.com.fitnesstracker.models.ActivenessType
import main.com.fitnesstracker.views.main.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddNewActivityDialog : AppCompatDialogFragment() {


    val myViewModel: MainViewModel by sharedViewModel()
    lateinit var spinner: Spinner


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.AlertDialogCustom)

        val inflater = activity?.layoutInflater

        if (inflater != null) {
            val view = inflater.inflate(R.layout.fragment_new_activeness_dialog, null)

            with(builder) {
                setView(view)
                setPositiveButton(R.string.ok_button_name) {dialog, _ ->
                    if(spinner != null){
                        when(spinner.selectedItem.toString()){
                            resources.getString(R.string.action_endurance) ->
                                myViewModel.addActiveness(ActivenessType.ENDURANCE)
                            resources.getString(R.string.action_swim) ->
                                myViewModel.addActiveness(ActivenessType.SWIMMING)
                            resources.getString(R.string.action_strength) ->
                                myViewModel.addActiveness(ActivenessType.STRENGTH)
                        }
                    }
                    dialog.cancel()
                }

            }

            spinner = view?.findViewById(R.id.activeness_type_spinner)!!

            ArrayAdapter.createFromResource(
                context,
                R.array.activeness_type_array, R.layout.layout_spinner_item
            ).also {
                it.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = it
            }
        }



        return builder.create()
    }

}