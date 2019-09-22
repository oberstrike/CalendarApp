package main.com.calendarapp.views.main.fragments

import android.content.Context
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import main.com.calendarapp.R
import main.com.calendarapp.models.Activeness


class RecyclerViewAdapter(val context: Context,val activenesses : ArrayList<Activeness>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    val TAG: String ="RecyclerViewAdapter"

    class ViewHolder(itemView: View,val onClickListener: OnClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val image: ImageView = itemView.findViewById(R.id.image_view)
        val imageName: TextView = itemView.findViewById(R.id.text_view)
        val parentLayout: RelativeLayout = itemView.findViewById(R.id.parent_layout)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickListener.onItemClick(adapterPosition)
        }

    }

    fun  getItem(position: Int): Activeness {
        return activenesses[position]
    }

    override fun getItemCount(): Int {
        return activenesses.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(R.mipmap.ic_launcher)
        val activeness = activenesses[position]
        val date = activeness.date
        val str = date.toString()

        holder.imageName.text = str

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(view, onClickListener)
    }

    interface OnClickListener {
        fun onItemClick(position: Int)
    }
}

