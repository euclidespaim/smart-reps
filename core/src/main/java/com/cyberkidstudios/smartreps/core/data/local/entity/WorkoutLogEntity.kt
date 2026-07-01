package com.cyberkidstudios.smartreps.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "workout_logs",
    foreignKeys = [
        ForeignKey(entity = WorkoutEntity::class, parentColumns = ["id"], childColumns = ["workoutId"], onDelete = ForeignKey.SET_NULL)
    ],
    indices = [Index("workoutId")]
)
data class WorkoutLogEntity(
    @PrimaryKey val id: String,
    val workoutId: String?,
    val startedAt: Long,
    val completedAt: Long?,
    val totalVolume: Int,
    val durationMinutes: Int,
    val status: String, // "IN_PROGRESS", "COMPLETED", "CANCELED"
    val source: String  // "mobile", "wear"
)
