package main.com.calendarapp.views.exercise.fragments

import android.content.Context
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import main.com.calendarapp.R
import main.com.calendarapp.ext.convertDateTimeToHeadline
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.Exercise
import main.com.calendarapp.models.WorkoutSet
import org.joda.time.format.DateTimeFormatter


class WorkoutSetRecyclerViewAdapter(
    val context: Context,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<WorkoutSetRecyclerViewAdapter.ViewHolder>() {

    var workouts = ArrayList<WorkoutSet>()

    class ViewHolder(itemView: View, private val onClickListener: OnClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val image: ImageView = itemView.findViewById(R.id.image_view)
        val imageName: TextView = itemView.findViewById(R.id.text_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickListener.onItemClick(adapterPosition)
        }

    }

    fun getItem(position: Int): WorkoutSet {
        return workouts[position]
    }

    override fun getItemCount(): Int {
        return workouts.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(R.mipmap.ic_launcher)
        val workout = workouts[position]

        holder.imageName.text = "${workout.name}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(view, onClickListener)
    }

    interface OnClickListener {
        fun onItemClick(position: Int)
    }
}

