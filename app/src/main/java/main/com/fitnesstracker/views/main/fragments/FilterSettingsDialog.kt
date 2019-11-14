package main.com.fitnesstracker.views.main.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDialogFragment
import main.com.fitnesstracker.R
import main.com.fitnesstracker.util.FilterType
import main.com.fitnesstracker.util.MainContext

class FilterSettingsDialog : AppCompatDialogFragment(), View.OnClickListener {

    private var filterType = MainContext.settings.filterType

    private lateinit var radioButtonName: RadioButton
    private lateinit var radioButtonDate: RadioButton
    private lateinit var radioButtonWeekday: RadioButton


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.AlertDialogCustom)

        val inflater = activity?.layoutInflater

        if (inflater != null) {
            val view = inflater.inflate(R.layout.fragment_filter_dialog, null)

            radioButtonName = view.findViewById(R.id.radiobutton_name)
            radioButtonName.setOnClickListener(this)

            radioButtonDate = view.findViewById(R.id.radiobutton_date)
            radioButtonDate.setOnClickListener(this)

            radioButtonWeekday = view.findViewById(R.id.radiobutton_weekday)
            radioButtonWeekday.setOnClickListener(this)

            with(builder) {
                setView(view)
                setPositiveButton("Ok") { _, _ ->
                    MainContext.settings.filterType = filterType
                }
            }

        }
        return builder.create()
    }


    override fun onClick(v: View?) {
        if (v != null) {
            filterType =
                when (v.id) {
                    R.id.radiobutton_name -> FilterType.NAME
                    R.id.radiobutton_date -> FilterType.DATE
                    R.id.radiobutton_weekday -> FilterType.WEEKDAY
                    else -> FilterType.NAME
                }
        }

    }
}