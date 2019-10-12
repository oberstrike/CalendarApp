package main.com.calendarapp.views.activeness

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_activeness.*
import kotlinx.android.synthetic.main.content_activeness.*
import main.com.calendarapp.R
import main.com.calendarapp.views.activeness.fragments.AddNewExerciseDialog
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
        val id = intent.getLongExtra("Id", 0)
        myViewModel.init(id)
        initRecyclerView()
        btn_add.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        exerciseRecyclerViewAdapter = ExerciseRecyclerViewAdapter(this, this)
        exerciseRecyclerView.adapter = exerciseRecyclerViewAdapter
        exerciseRecyclerView.layoutManager = LinearLayoutManager(this)

        myViewModel.launch {
            myViewModel.getActiveActiveness()
                .subscribeOn(myViewModel.provider.computation())
                .observeOn(myViewModel.provider.ui())
                .subscribe {
                    exerciseRecyclerViewAdapter.exercises = ArrayList(it.first().exercises)
                    exerciseRecyclerViewAdapter.notifyDataSetChanged()
            }
        }

    }


    //Öffnet eine bestehende Übung
    override fun onItemClick(position: Int) {

        myViewModel.startJob =
            myViewModel.getActiveActiveness()
                .subscribeOn(myViewModel.provider.computation())
                .observeOn(myViewModel.provider.ui())
                .subscribe {
                    val first = it.first()
                    myViewModel.setActiveExercise(first.exercises[position].id)
                    val workoutSetCount = first.exercises[position].workoutSets.size
                    val intent = Intent(this, ExerciseActivity::class.java)
                    intent.putExtra("Count", workoutSetCount)
                    startActivity(intent)
                }


    }

    //Öffnet den Dialog zum Erstellen einer neuen Übung
    override fun onClick(v: View?) {
       val addNewExerciseDialog = AddNewExerciseDialog()
        addNewExerciseDialog.show(supportFragmentManager, "new Dialog")
    }

    override fun onPause() {
        super.onPause()
        myViewModel.onPause()
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item != null) {

            when (item.itemId) {
                //Wenn "Löschen" ausgewählt ist
                R.id.action_delete -> {
                    onActionDeleteExercise()
                }
                R.id.action_rename -> {
                    onActionRenameExercise()
                }
            }
        }
        return super.onContextItemSelected(item)
    }


    fun onActionDeleteExercise() {
        val position = exerciseRecyclerViewAdapter.position
        if (myViewModel.deleteExercise(exerciseRecyclerViewAdapter.getItem(position))) {
            Toast.makeText(this, "Gelöscht", Toast.LENGTH_LONG)
        }
    }

    fun onActionRenameExercise() {


    }
}
