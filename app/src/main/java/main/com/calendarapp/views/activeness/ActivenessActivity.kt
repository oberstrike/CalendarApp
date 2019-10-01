package main.com.calendarapp.views.activeness

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.objectbox.android.AndroidScheduler
import kotlinx.android.synthetic.main.activity_activeness.*
import kotlinx.android.synthetic.main.content_activeness.*
import main.com.calendarapp.R
import main.com.calendarapp.views.activeness.fragments.ExerciseRecyclerViewAdapter
import main.com.calendarapp.views.exercise.ExerciseActivity
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
        val builder = AlertDialog.Builder(this)
        val setText = EditText(this)
        setText.hint = "Sets"
        setText.inputType = InputType.TYPE_CLASS_TEXT
        var input: String


        with(builder){
            setTitle("Workout Set")
            setView(setText)
            setPositiveButton("OK") { dialog, _  ->
                onPositiveButtonClick(dialog, setText.text.toString())
            }
            setNegativeButton("CANCEL") {dialog, _ ->
                   dialog.cancel()
            }
            show()
        }

    }

    override fun onClick(v: View?) {
        myViewModel.addExercise()
    }

    fun onPositiveButtonClick(dialog: DialogInterface, text: String ){

        val intent = Intent(this, ExerciseActivity::class.java)

        intent.putExtra("data", text)

        startActivity(intent)

        dialog.cancel()
    }


}
