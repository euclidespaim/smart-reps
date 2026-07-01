package com.cyberkidstudios.smartreps.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cyberkidstudios.smartreps.core.data.local.entity.*
import com.cyberkidstudios.smartreps.core.data.local.dao.*

@Database(
    entities = [
        ExerciseEntity::class,
        WorkoutEntity::class,
        WorkoutExerciseEntity::class,
        WorkoutLogEntity::class,
        SetLogEntity::class,
        UserPreferencesEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutLogDao(): WorkoutLogDao
}
