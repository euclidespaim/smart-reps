package com.cyberkidstudios.smartreps.core.domain.model

data class ActiveWorkoutExercise(
    val workoutExerciseId: String,
    val exercise: Exercise,
    val targetSets: Int,
    val targetReps: Int,
    val targetWeight: Float,
    val restSeconds: Int,
    val orderIndex: Int
)
