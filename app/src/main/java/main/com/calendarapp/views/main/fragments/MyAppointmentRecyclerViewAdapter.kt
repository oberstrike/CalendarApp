package main.com.calendarapp.views.main.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import main.com.calendarapp.R


import main.com.calendarapp.views.main.fragments.AppointmentFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_appointment.view.*
import main.com.calendarapp.models.Appointment

/**
 * [RecyclerView.Adapter] that can display a [Appointment] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MyAppointmentRecyclerViewAdapter(
    private val mValues: List<Appointment>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyAppointmentRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Appointment
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_appointment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mContentView.text = item.content

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
