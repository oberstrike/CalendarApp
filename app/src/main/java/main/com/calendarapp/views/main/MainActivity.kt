package main.com.calendarapp.views.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import main.com.calendarapp.R
import main.com.calendarapp.models.Appointment
import main.com.calendarapp.views.day.DayActivity
import main.com.calendarapp.views.main.fragments.AppointmentFragment
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), AppointmentFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: Appointment?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val logger: Logger = Logger.getLogger("MainLogger")

    val myViewModel: MainViewModel by viewModel()

    override fun onDestroy() {
        super.onDestroy()
        logger.info("Destroy")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        logger.info(myViewModel.toString())

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
           val newIntent = Intent(this, DayActivity::class.java)
            newIntent.putExtra("Date",  intArrayOf(year, month, dayOfMonth))
            startActivity(newIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
