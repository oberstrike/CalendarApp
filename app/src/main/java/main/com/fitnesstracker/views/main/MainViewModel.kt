package main.com.fitnesstracker.views.main


import main.com.fitnesstracker.util.rx.SchedulerProvider
import main.com.fitnesstracker.views.AbstractViewModel

class MainViewModel(
    val provider: SchedulerProvider
) : AbstractViewModel()