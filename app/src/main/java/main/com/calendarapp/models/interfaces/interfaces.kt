package main.com.calendarapp.models.interfaces

interface StrengthWorkoutSet{
    var repetitions: Long
    var weight: Long
}

interface EnduranceWorkoutSet{
    var distance: Long
    var time: Long
}

interface SwimWorkoutSet{
    var time: Long
    var lanes: Long
}

interface SelfWeightWorkoutSet{
    var repetitions: Long
}
