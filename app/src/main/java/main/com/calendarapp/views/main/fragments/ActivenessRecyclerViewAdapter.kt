package main.com.calendarapp.views.main.fragments

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import main.com.calendarapp.R
import main.com.calendarapp.ext.convertDateTimeToHeadline
import main.com.calendarapp.models.Activeness
import main.com.calendarapp.models.ActivenessType


class ActivenessRecyclerViewAdapter(
    val context: Context,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ActivenessRecyclerViewAdapter.ViewHolder>() {

    var activenesses: ArrayList<Activeness> = ArrayList()

    var position: Int = 0

    class ViewHolder(
        itemView: View,
        private val onClickListener: OnClickListener
    ) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnCreateContextMenuListener {

        val image: ImageView = itemView.findViewById(R.id.image_view)
        val imageName: TextView = itemView.findViewById(R.id.text_view)


        init {
            itemView.setOnClickListener(this)
            itemView.setOnCreateContextMenuListener(this)

        }

        override fun onClick(v: View?) {
            onClickListener.onItemClick(adapterPosition)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.add(Menu.NONE, R.id.action_delete, Menu.NONE, R.string.action_delete)
            menu?.add(Menu.NONE, R.id.action_rename, Menu.NONE, R.string.action_rename)
        }


    }


    fun getItem(position: Int): Activeness {
        return activenesses[position]
    }

    override fun getItemCount(): Int {
        return activenesses.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val activeness = activenesses[position]
        val date = activeness.date

        when(activeness.type){
            ActivenessType.STRENGTH -> holder.image.setImageResource(R.drawable.strength)
            ActivenessType.ENDURANCE -> holder.image.setImageResource(R.drawable.endurance)
            ActivenessType.SWIMMING -> holder.image.setImageResource(R.drawable.swim)
        }

        holder.itemView.setOnLongClickListener {
            this.position = holder.adapterPosition
            false
        }
        if(activeness.name == "")
        {
            holder.imageName.text = convertDateTimeToHeadline(date)
        }else
        {
            holder.imageName.text = activeness.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(view, onClickListener)
    }

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.itemView.setOnLongClickListener(null)
        super.onViewRecycled(holder)
    }
}

