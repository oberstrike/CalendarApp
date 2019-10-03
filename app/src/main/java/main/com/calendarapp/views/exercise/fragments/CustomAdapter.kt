package main.com.calendarapp.views.exercise.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import main.com.calendarapp.R
import main.com.calendarapp.models.WorkoutSet
import org.w3c.dom.Text

class CustomAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val items: List<WorkoutSet>):
    ArrayAdapter<WorkoutSet>(context, layoutResource, items), View.OnClickListener {

    private class ViewHolder(row: View?){
        var textView: TextView?

        init {
            this.textView = row?.findViewById<TextView>(R.id.textViewSet)
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var result: View
        val viewHolder: ViewHolder

        if(convertView == null){
            val inflater = LayoutInflater.from(context)
            result = inflater.inflate(R.layout.fragment_workout, null)
            viewHolder = ViewHolder(result)
            result.tag = viewHolder
        }else{
            result = convertView
            viewHolder = result.tag as ViewHolder
        }
        var workoutSet = items[position]
        viewHolder.textView?.text = workoutSet.name

        return result
    }

    override fun getItem(position: Int): WorkoutSet? {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }




    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}