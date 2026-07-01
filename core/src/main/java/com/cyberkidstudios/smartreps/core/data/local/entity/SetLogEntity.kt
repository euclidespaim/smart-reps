package com.cyberkidstudios.smartreps.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "set_logs",
    foreignKeys = [
        ForeignKey(entity = WorkoutLogEntity::class, parentColumns = ["id"], childColumns = ["workoutLogId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = WorkoutExerciseEntity::class, parentColumns = ["id"], childColumns = ["workoutExerciseId"], onDelete = ForeignKey.SET_NULL)
    ],
    indices = [Index("workoutLogId"), Index("workoutExerciseId")]
)
data class SetLogEntity(
    @PrimaryKey val id: String,
    val workoutLogId: String,
    val workoutExerciseId: String?,
    val setNumber: Int,
    val repsCompleted: Int,
    val weightUsed: Float,
    val isWarmup: Boolean,
    val isDropSet: Boolean,
    val isFailure: Boolean,
    val rpe: String?,
    val completedAt: Long,
    val source: String // "mobile", "wear"
)
