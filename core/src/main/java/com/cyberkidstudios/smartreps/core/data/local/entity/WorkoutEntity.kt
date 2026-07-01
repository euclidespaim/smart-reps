package com.cyberkidstudios.smartreps.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val dayOfWeek: Int,
    val muscleGroups: String,
    val orderIndex: Int,
    val createdAt: Long,
    val updatedAt: Long
)
