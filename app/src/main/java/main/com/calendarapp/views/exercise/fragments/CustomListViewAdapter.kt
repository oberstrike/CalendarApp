package main.com.calendarapp.views.exercise.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import main.com.calendarapp.R
import main.com.calendarapp.models.WorkoutSet

class CustomListViewAdapter(
    context: Context, @LayoutRes private val layoutResource: Int,
    var items: List<WorkoutSet>
) :
    ArrayAdapter<WorkoutSet>(context, layoutResource, items), View.OnClickListener {

    class ViewHolder(row: View?) {
        var textView: TextView? = row?.findViewById(R.id.textViewSet)
        var firstAttributeEditText: EditText? = row?.findViewById(R.id.secondAttributeEditText)
        var secondAttributeEditText: EditText? = row?.findViewById(R.id.firstAttributeEditText)
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

        viewHolder.textView?.text = "${position + 1}. Satz"
        viewHolder.firstAttributeEditText?.hint = items[position].repetitions.toString()
        viewHolder.secondAttributeEditText?.hint = items[position].weight.toString()

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