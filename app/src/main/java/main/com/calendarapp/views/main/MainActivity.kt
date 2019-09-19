package main.com.calendarapp.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.reactivex.functions.Consumer

import kotlinx.android.synthetic.main.activity_main.*
import main.com.calendarapp.R
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    val myViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
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
}
