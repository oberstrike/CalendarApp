package main.com.fitnesstracker.views.main.fragments

import android.content.Context
import android.util.Log
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import main.com.fitnesstracker.R
import main.com.fitnesstracker.ext.Weekday
import main.com.fitnesstracker.models.Activeness
import main.com.fitnesstracker.models.ActivenessType
import main.com.fitnesstracker.util.FilterType
import main.com.fitnesstracker.util.MainContext
import org.joda.time.format.DateTimeFormat


class ActivenessRecyclerViewAdapter(
    val context: Context,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ActivenessRecyclerViewAdapter.ViewHolder>(), Filterable {

    private var activenesses: ArrayList<Activeness> = ArrayList()
    private var activenessesFull: ArrayList<Activeness> = ArrayList()

    fun setActivenesses(activenesses: ArrayList<Activeness>) {
        val page = MainContext.settings.page
        this.activenesses = ArrayList(activenesses.drop(page * 10).take(10))
        this.activenessesFull = ArrayList(activenesses)
    }

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
        when(activeness.type){
            ActivenessType.STRENGTH -> holder.image.setImageResource(R.drawable.strength)
            ActivenessType.ENDURANCE -> holder.image.setImageResource(R.drawable.endurance)
            ActivenessType.SWIMMING -> holder.image.setImageResource(R.drawable.swim)
        }

        holder.itemView.setOnLongClickListener {
            this.position = holder.adapterPosition
            false
        }
        holder.imageName.text = activeness.name

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view, onClickListener)
    }

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.itemView.setOnLongClickListener(null)
        super.onViewRecycled(holder)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterType = MainContext.settings.filterType
                Log.i("Info", filterType.toString())

                val filteredList = ArrayList<Activeness>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(activenessesFull)
                } else {
                    val pattern = constraint.toString()


                    for (activeness in activenessesFull) {
                        if (filterType == FilterType.NAME) {
                            if (activeness.name.contains(pattern)) {
                                filteredList.add(activeness)
                            }
                        } else if (filterType == FilterType.WEEKDAY) {
                            if (Weekday.byId(activeness.date.dayOfWeek).toString().contains(pattern.toLowerCase())) {
                                filteredList.add(activeness)
                            }
                        } else {
                            val formatter = DateTimeFormat.forPattern("dd.MM.yyyy")
                            val date = activeness.date.toString(formatter)
                            if (date.contains(pattern)) {
                                filteredList.add(activeness)
                            }
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                activenesses.clear()
                activenesses.addAll(results?.values as Collection<Activeness>)
                notifyDataSetChanged()
            }
        }
    }

}

