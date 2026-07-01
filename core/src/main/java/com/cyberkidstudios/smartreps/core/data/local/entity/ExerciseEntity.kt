package com.cyberkidstudios.smartreps.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey val id: String,
    val namePt: String,
    val nameEn: String,
    val muscleGroup: String,
    val equipmentType: String,
    val gifLocalPath: String?,
    val instructionsPt: String,
    val instructionsEn: String,
    val defaultSets: Int,
    val defaultReps: Int,
    val defaultWeight: Float,
    val defaultRestSeconds: Int
)
