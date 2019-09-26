package main.com.calendarapp.views.activeness

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.objectbox.android.AndroidScheduler
import kotlinx.android.synthetic.main.activity_activeness.*
import kotlinx.android.synthetic.main.content_activeness.*
import main.com.calendarapp.R
import main.com.calendarapp.views.activeness.fragments.ExerciseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ActivenessActivity : AppCompatActivity(), ExerciseRecyclerViewAdapter.OnClickListener,
    View.OnClickListener {

    private val myViewModel: ActivenessViewModel by viewModel()
    lateinit var exerciseRecyclerViewAdapter: ExerciseRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activeness)
        setSupportActionBar(toolbar)
        val id: Long = intent.getLongExtra("Id", 0)
        myViewModel.init(id)
        initRecyclerView()
        btn_add.setOnClickListener(this)


    }

    private fun initRecyclerView() {
        exerciseRecyclerViewAdapter = ExerciseRecyclerViewAdapter(this, this)
        exerciseRecyclerView.adapter = exerciseRecyclerViewAdapter
        exerciseRecyclerView.layoutManager = LinearLayoutManager(this)

       myViewModel.subscription =  myViewModel.activeness.subscribe()
            .on(AndroidScheduler.mainThread())
            .observer{next ->
                exerciseRecyclerViewAdapter.exercises = ArrayList( next.first().exercises)
                exerciseRecyclerViewAdapter.notifyDataSetChanged()
            }


    }

    override fun onItemClick(position: Int) {
        Log.i("Position", position.toString())
    }

    override fun onClick(v: View?) {
        myViewModel.addExercise()
    }


}
