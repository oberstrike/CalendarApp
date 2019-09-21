package main.com.calendarapp.views.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import main.com.calendarapp.R
import main.com.calendarapp.views.activeness.ActivenessActivity
import main.com.calendarapp.views.main.fragments.RecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnClickListener {

    private val myViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initRecyclerView()
        initAddBtn()
        registerForContextMenu(recyclerView)
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


    private fun initRecyclerView(){

        myViewModel.launch {  myViewModel.activities
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe{next ->
                recyclerView.adapter = RecyclerViewAdapter(this, ArrayList(next), this)
            }
        }
        recyclerView.adapter = RecyclerViewAdapter(this, ArrayList(), this)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun initAddBtn() {
        btn_add.setOnClickListener{
            myViewModel.addActiveness()
        }
    }

    override fun onItemClick(position: Int) {
        val name: String? = myViewModel.activities.value?.get(position)
        val intent = Intent(this, ActivenessActivity::class.java)
        intent.putExtra("Data", name)
        startActivity(intent)
    }
}
