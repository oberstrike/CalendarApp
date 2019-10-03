package main.com.calendarapp.views.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.objectbox.android.AndroidScheduler

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import main.com.calendarapp.R
import main.com.calendarapp.views.activeness.ActivenessActivity
import main.com.calendarapp.views.main.fragments.RecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnClickListener {

    private val myViewModel: MainViewModel by viewModel()

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        init()
        initAddAndDeleteBtn()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //TODO Implementation of Settings and Statistics
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_statistics -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Initialisiert das RecylcerView und verbindet dieses mit dem ViewModel
    private fun init(){


        recyclerViewAdapter = RecyclerViewAdapter(this,this)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        myViewModel.subscription = myViewModel.activities
            .subscribe()
            .on(AndroidScheduler.mainThread())
            .observer{ entities ->
                recyclerViewAdapter.activenesses =  ArrayList(entities)
                recyclerViewAdapter.notifyDataSetChanged()
            }
    }

    //Fügt den Buttons ihre Funktionalitäten hinzu
    private fun initAddAndDeleteBtn() {
        btn_add.setOnClickListener{
            myViewModel.addActiveness()
        }

        btn_delete.setOnClickListener{
            myViewModel.deleteActiveness()
        }

    }

    //Öffnet
    override fun onItemClick(position: Int) {
        val intent = Intent(this, ActivenessActivity::class.java)
        val activeness = recyclerViewAdapter.getItem(position)

        intent.putExtra("Id", activeness.id)
        startActivity(intent)
    }
}
