package main.com.calendarapp.views.activeness.fragments

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import main.com.calendarapp.R
import main.com.calendarapp.models.Exercise


class ExerciseRecyclerViewAdapter(
    val context: Context,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {

    var exercises = ArrayList<Exercise>()

    var position: Int = 0

    class ViewHolder(itemView: View, private val onClickListener: OnClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener,
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

    fun getItem(position: Int): Exercise {
        return exercises[position]
    }

    override fun getItemCount(): Int {
        return exercises.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(R.mipmap.ic_launcher)
        holder.imageName.text = exercises[position].name

        holder.itemView.setOnLongClickListener {
            this.position = holder.adapterPosition
            false
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
}

