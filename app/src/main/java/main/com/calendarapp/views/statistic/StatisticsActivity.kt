package main.com.calendarapp.views.statistic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import main.com.calendarapp.R
import main.com.calendarapp.views.statistic.fragments.BarChartFragment
import org.koin.android.viewmodel.ext.android.viewModel

class StatisticsActivity : AppCompatActivity() {

    private val myViewModel by viewModel<StatisticsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val fragment = BarChartFragment()
        supportFragmentManager.beginTransaction().replace(R.id.barchart_container, fragment)
            .commit()
    }
}
