package com.cyberkidstudios.smartreps.core.di

import android.content.Context
import androidx.room.Room
import com.cyberkidstudios.smartreps.core.data.local.AppDatabase
import com.cyberkidstudios.smartreps.core.data.local.dao.ExerciseDao
import com.cyberkidstudios.smartreps.core.data.local.dao.WorkoutDao
import com.cyberkidstudios.smartreps.core.data.local.dao.WorkoutLogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "smartreps_db"
        )
        .fallbackToDestructiveMigration() // For development only
        .build()
    }

    @Provides
    fun provideExerciseDao(db: AppDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    fun provideWorkoutDao(db: AppDatabase): WorkoutDao = db.workoutDao()

    @Provides
    fun provideWorkoutLogDao(db: AppDatabase): WorkoutLogDao = db.workoutLogDao()

    @Provides
    @Singleton
    fun provideDatabaseSeeder(@ApplicationContext context: Context, exerciseDao: ExerciseDao): com.cyberkidstudios.smartreps.core.data.local.DatabaseSeeder {
        return com.cyberkidstudios.smartreps.core.data.local.DatabaseSeeder(context, exerciseDao)
    }
}
