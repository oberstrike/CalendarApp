package main.com.calendarapp.models.interfaces

interface StrengthWorkoutSet{
    var repetitions: Long
    var weight: Long
}

interface EnduranceWorkoutSet{
    var distance: Long
    var time: Float
}

interface SwimWorkoutSet{
    var time: Float
    var lanes: Long
}

interface TrainWithoutWeightWorkoutSet {
    var repetitions: Long
}
