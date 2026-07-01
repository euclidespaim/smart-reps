package com.cyberkidstudios.smartreps.core.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class WorkoutExerciseWithExercise(
    @Embedded val workoutExercise: WorkoutExerciseEntity,
    
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "id"
    )
    val exercise: ExerciseEntity
)
