package main.com.calendarapp.views.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.pwittchen.swipe.library.rx2.Swipe
import com.github.pwittchen.swipe.library.rx2.SwipeEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import main.com.calendarapp.R
import main.com.calendarapp.views.activeness.ActivenessActivity
import main.com.calendarapp.views.main.fragments.ActivenessRecyclerViewAdapter
import main.com.calendarapp.views.main.fragments.AddNewActivityDialog
import main.com.calendarapp.views.main.fragments.FilterSettingsDialog
import main.com.calendarapp.views.main.fragments.RenameActivenessDialog
import main.com.calendarapp.views.statistic.StatisticsActivity
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(),
    ActivenessRecyclerViewAdapter.OnClickListener {

    private val myViewModel: MainViewModel by viewModel()

    private lateinit var activenessRecyclerViewAdapter: ActivenessRecyclerViewAdapter

    private val swipe = Swipe(40, 200)

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
            override fun onQueryTextSubmit(newText: String): Boolean {
                activenessRecyclerViewAdapter.filter.filter(newText)
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
        return when (item.itemId) {
            R.id.action_statistics -> {
                val intent = Intent(this, StatisticsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_filter -> {
                val filterSettingsDialog = FilterSettingsDialog()
                filterSettingsDialog.show(supportFragmentManager, "Change filter settings")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Initialisiert das RecylcerView und verbindet dieses mit dem ViewModel
    private fun init(){

        activenessRecyclerViewAdapter = ActivenessRecyclerViewAdapter(this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = activenessRecyclerViewAdapter

        //RecyclerView
        myViewModel.launch {
            myViewModel.getAllActiveness()
                .subscribeOn(myViewModel.provider.computation())
                .observeOn(myViewModel.provider.ui())
                .doOnError {
                    println(it)
                }
                .map {
                    for (activeness in it) {
                        if (activeness.name.isEmpty()) {
                            myViewModel.deleteActiveness(activeness)

                        }
                    }
                    it.filter { each -> each.name.isNotEmpty() }
                }
                .subscribe {
                    activenessRecyclerViewAdapter.setActivenesses(ArrayList(it.asReversed()))
                    activenessRecyclerViewAdapter.notifyDataSetChanged()
                }
        }

        //Seitenausgabe
        myViewModel.launch {
            myViewModel.page.subscribeOn(myViewModel.provider.computation())
                .observeOn(myViewModel.provider.ui()).subscribe {
                    val page = it + 1
                    val pages = myViewModel.pages.value
                    if (pages != 1)
                        pageTextView.text = "Seite $page von $pages"
                    else
                        pageTextView.text = ""
                }
        }

        myViewModel.launch {
            myViewModel.pages.subscribeOn(myViewModel.provider.computation())
                .observeOn(myViewModel.provider.ui()).subscribe {
                    val page = myViewModel.page.value?.toInt() ?: 0
                    val pages = it
                    if (pages != 1)
                        pageTextView.text = "Seite ${page + 1} von $pages"
                    else
                        pageTextView.text = ""
                }
        }

        myViewModel.launch {
            swipe.observe().subscribeOn(myViewModel.provider.computation())
                .observeOn(myViewModel.provider.ui())
                .subscribe {
                    val activePage = myViewModel.page.value!!
                    val pages = myViewModel.pages.value!!

                    if (SwipeEvent.SWIPED_LEFT == it) {
                        if (activePage > 0) {
                            myViewModel.page.onNext(activePage - 1)

                        }
                    } else if (SwipeEvent.SWIPED_RIGHT == it) {
                        if (activePage < pages - 1) {
                            myViewModel.page.onNext(activePage + 1)
                        }
                    }

                }
        }
    }

    //Fügt den Buttons ihre Funktionalitäten hinzu
    private fun initAddAndDeleteBtn() {

        btn_add.setOnClickListener{
            val dialog = AddNewActivityDialog()
            dialog.show(supportFragmentManager, "Add new Activeness Dialog")
        }

        btn_delete.setOnClickListener{
            myViewModel.deleteAllActiveness()
        }

    }

    //Öffnet eine Aktivität
    override fun onItemClick(position: Int) {
        val intent = Intent(this, ActivenessActivity::class.java)
        val activeness = activenessRecyclerViewAdapter.getItem(position)

        if (myViewModel.isReadyToSwitch(activeness)) {
            startActivity(intent)
        }

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

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        swipe.dispatchTouchEvent(event)
        return super.dispatchTouchEvent(event)
    }

}
