package main.com.calendarapp.views.statistic

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.github.pwittchen.swipe.library.rx2.Swipe
import com.github.pwittchen.swipe.library.rx2.SwipeEvent
import main.com.calendarapp.R
import main.com.calendarapp.views.statistic.fragments.ActivenessBarChartFragment
import main.com.calendarapp.views.statistic.fragments.ExerciseBarChartFragment
import org.koin.android.viewmodel.ext.android.viewModel

class StatisticsActivity : AppCompatActivity() {

    private val myViewModel by viewModel<StatisticsViewModel>()
    private lateinit var swipe: Swipe

    private var status: ActiveFragment = ActiveFragment.ACTIVENESSBARCHART

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val activenessBarChartFragment = ActivenessBarChartFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.barchart_container, activenessBarChartFragment)
            .commit()

        swipe = Swipe(40, 200)

        myViewModel.launch {
            swipe.observe().subscribeOn(myViewModel.schedulerProvider.computation())
                .observeOn(myViewModel.schedulerProvider.ui())
                .subscribe {
                    if (it == SwipeEvent.SWIPED_RIGHT && status == ActiveFragment.ACTIVENESSBARCHART) {
                        val exerciseBarChartFragment = ExerciseBarChartFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                            .replace(R.id.barchart_container, exerciseBarChartFragment)
                            .commit()
                        status = ActiveFragment.EXERCISEBARCHART
                    } else if (it == SwipeEvent.SWIPED_LEFT && status == ActiveFragment.EXERCISEBARCHART) {
                        val activenessBarChartFragment = ActivenessBarChartFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                            .replace(R.id.barchart_container, activenessBarChartFragment)
                            .commit()
                        status = ActiveFragment.ACTIVENESSBARCHART
                    }
                }

        }
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        swipe.dispatchTouchEvent(event)
        return super.dispatchTouchEvent(event)
    }


    enum class ActiveFragment {
        ACTIVENESSBARCHART, EXERCISEBARCHART
    }
}
