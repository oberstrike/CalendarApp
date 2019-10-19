package main.com.calendarapp.views.exercise.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import main.com.calendarapp.R
import main.com.calendarapp.ext.afterTextChanged
import main.com.calendarapp.models.WorkoutSet
import main.com.calendarapp.views.exercise.fragments.interfaces.Fillable
import main.com.calendarapp.views.exercise.fragments.interfaces.OnTextChangeListener
import okhttp3.internal.toLongOrDefault

class EnduranceTrainingRecyclerViewAdapter(
    val context: Context,
    private val onTextChangeListener: OnTextChangeListener
) : RecyclerView.Adapter<EnduranceTrainingRecyclerViewAdapter.ViewHolder>(),
    Fillable {

    private var items = ArrayList<WorkoutSet>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val distance: EditText = itemView.findViewById(R.id.firstAttributeEditText)
        val time: EditText = itemView.findViewById(R.id.secondAttributeEditText)
        //  val distanceText: TextView = itemView.findViewById(R.id.firstAttributeTextView)
        // val timeText: TextView = itemView.findViewById(R.id.secondAttributeTextView)
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

        holder.distance.hint = items[position].distance.toString()

        holder.distance.afterTextChanged {
            if (it.isNotEmpty()) {
                items[position].distance = it.toLongOrDefault(items[position].distance)
                this.onTextChangeListener.onChange(items[position])
            }
        }

        //    holder.distanceText.text = context.resources.getText(R.string.distance)

        holder.time.hint = items[position].time.toString()
        holder.time.afterTextChanged {
            if (it.isNotEmpty()) {
                val newValue = it.toFloatOrNull()
                if (newValue != null)
                    items[position].time = newValue
                else
                    items[position].time = items[position].time

                this.onTextChangeListener.onChange(items[position])
            }
        }
        //      holder.timeText.text = context.resources.getText(R.string.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_workset_with_two_attributes, parent, false)
        return ViewHolder(view)
    }

    override fun setItems(list: ArrayList<WorkoutSet>) {
        if (list == null) {
            return
        }

        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


}