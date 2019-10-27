package main.com.calendarapp.views.statistic.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import main.com.calendarapp.R
import main.com.calendarapp.views.statistic.StatisticsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ActivenessBarChartFragment : Fragment() {


    private val myViewModel: StatisticsViewModel by sharedViewModel()
    private lateinit var barChart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_barchart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        barChart = BarChartObject.createNewBarChart(view)
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return BarChartObject.getXAxisValues()[value.toInt() - 1]
            }
        }
        initDataSet()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDataSet() {
        myViewModel.launch {
            myViewModel.getAllActiveness().subscribeOn(myViewModel.schedulerProvider.computation())
                .observeOn(myViewModel.schedulerProvider.ui())
                .subscribe {
                    val grouped = it.groupBy { it.date.monthOfYear() }
                    val valueSet = ArrayList<BarEntry>()
                    for (key in grouped.keys) {
                        val month = key.get().toFloat()
                        val sum = grouped[key]?.count()?.toFloat()
                        if (sum != null)
                            valueSet.add(BarEntry(month, sum))
                        else
                            valueSet.add(BarEntry(month, 0f))
                    }

                    val barDataSet = BarDataSet(valueSet, "Trainingseinheiten")
                    barDataSet.color = Color.rgb(20, 155, 20)
                    barDataSet.valueTextSize = 12f
                    barChart.data = BarData(barDataSet)

                }
        }
    }
}