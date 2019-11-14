package main.com.fitnesstracker.models.interfaces

interface IStrengthWorkoutSet {
    var repetitions: Long
    var weight: Float
}

interface IEnduranceWorkoutSet {
    var distance: Long
    var time: Float
}

interface ISwimWorkoutSet {
    var time: Float
    var lanes: Long
}

interface ITrainWithoutWeightWorkoutSet {
    var repetitions: Long
}
