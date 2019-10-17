package main.com.calendarapp.views.exercise.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import main.com.calendarapp.R
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.util.afterTextChanged
import okhttp3.internal.toLongOrDefault

class TrainingWithoutWeightRecyclerViewAdapter(
    val context: Context,
    private val onTextChangeListener: OnTextChangeListener
) : RecyclerView.Adapter<TrainingWithoutWeightRecyclerViewAdapter.ViewHolder>(), Fillable {

    override var items = ArrayList<WorkoutSet>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val repetitions: EditText = itemView.findViewById(R.id.firstAttributeEditText)
        val setTextView: TextView = itemView.findViewById(R.id.textViewSet)
    }

    fun getItem(position: Int): WorkoutSet {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setTextView.text = "${position + 1}."
        holder.repetitions.hint = items[position].repetitions.toString()

        holder.repetitions.afterTextChanged {
            if (it.isNotEmpty()) {
                items[position].repetitions = it.toLongOrDefault(items[position].repetitions)
                this.onTextChangeListener.onChange(items[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_workset_with_one_attribute, parent, false)
        return ViewHolder(view)
    }


}

