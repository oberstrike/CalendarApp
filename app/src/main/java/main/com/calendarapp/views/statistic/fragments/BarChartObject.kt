package main.com.calendarapp.views.statistic.fragments

import android.view.View
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.formatter.ValueFormatter
import main.com.calendarapp.R
import kotlin.math.floor

object BarChartObject {

    fun createNewBarChart(view: View): BarChart {
        val barChart = view.findViewById<BarChart>(R.id.chart1)
        barChart.description.isEnabled = false
        barChart.setPinchZoom(false)
        barChart.setDrawGridBackground(false)
        barChart.setScaleEnabled(false)
        barChart.description.text = "Meine Beschreibung"
        barChart.animateXY(100, 100)
        barChart.invalidate()

        barChart.axisLeft.granularity = 1f
        barChart.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return floor(value).toInt().toString()
            }
        }
        barChart.axisRight.granularity = 1f
        barChart.axisRight.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return floor(value).toInt().toString()
            }
        }
        return barChart
    }


    fun getXAxisValues(): MutableList<String> {
        val xAxis = ArrayList<String>()
        xAxis.add("JAN")
        xAxis.add("FEB")
        xAxis.add("MAR")
        xAxis.add("APR")
        xAxis.add("MAY")
        xAxis.add("JUN")
        xAxis.add("JUL")
        xAxis.add("AUG")
        xAxis.add("SEP")
        xAxis.add("OCT")
        xAxis.add("NOV")
        xAxis.add("DEZ")
        return xAxis
    }


}