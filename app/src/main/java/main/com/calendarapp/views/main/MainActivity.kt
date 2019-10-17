package main.com.calendarapp.views.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import main.com.calendarapp.R
import main.com.calendarapp.views.activeness.ActivenessActivity
import main.com.calendarapp.views.main.fragments.ActivenessRecyclerViewAdapter
import main.com.calendarapp.views.main.fragments.AddNewActivityDialog
import main.com.calendarapp.views.main.fragments.RenameActivenessDialog
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(),
    ActivenessRecyclerViewAdapter.OnClickListener {

    private val myViewModel: MainViewModel by viewModel()

    private lateinit var activenessRecyclerViewAdapter: ActivenessRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()
        initAddAndDeleteBtn()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                activenessRecyclerViewAdapter.filter.filter(newText)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
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

        activenessRecyclerViewAdapter = ActivenessRecyclerViewAdapter(this, this)
        recyclerView.adapter = activenessRecyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        myViewModel.launch {
            myViewModel.getAllActiveness()
                .subscribeOn(myViewModel.provider.computation())
                .observeOn(myViewModel.provider.ui())
                .doOnError {
                    println(it)
                }
                .subscribe {
                    activenessRecyclerViewAdapter.setActivenesses(ArrayList(it.asReversed()))
                    activenessRecyclerViewAdapter.notifyDataSetChanged()
                }
        }
    }

    //Fügt den Buttons ihre Funktionalitäten hinzu
    private fun initAddAndDeleteBtn() {

        btn_add.setOnClickListener{
            val dialog = AddNewActivityDialog()
            dialog.show(supportFragmentManager, "Example Dialog")
        }

        btn_delete.setOnClickListener{
            myViewModel.deleteAllActiveness()
        }

    }

    //Öffnet eine Aktivität
    override fun onItemClick(position: Int) {
        val intent = Intent(this, ActivenessActivity::class.java)
        val activeness = activenessRecyclerViewAdapter.getItem(position)

        intent.putExtra("Id", activeness.id)
        startActivity(intent)
    }


    //Handelt das Contex-Menü
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                //Wenn "Löschen" ausgewählt ist
                R.id.action_delete -> {
                    onActionDeleteActiveness()
                }
                R.id.action_rename -> {
                    onActionRenameActiveness()
                }
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun onActionRenameActiveness() {
        val activeness =
            activenessRecyclerViewAdapter.getItem(activenessRecyclerViewAdapter.position)

        val dialogFragment = RenameActivenessDialog(activeness)
        dialogFragment.show(supportFragmentManager, "Activeness umbenennen")
    }

    private fun onActionDeleteActiveness() {
        val position = activenessRecyclerViewAdapter.position
        //Muss eventuell noch async laufen
        if (myViewModel.deleteActiveness(activenessRecyclerViewAdapter.getItem(position))) {
            Toast.makeText(this, "Gelöscht", Toast.LENGTH_LONG)
        }
    }

}
