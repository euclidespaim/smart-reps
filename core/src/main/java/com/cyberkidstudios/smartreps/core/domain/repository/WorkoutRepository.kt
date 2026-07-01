package com.cyberkidstudios.smartreps.core.domain.repository

import com.cyberkidstudios.smartreps.core.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutEntity

interface WorkoutRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>
    
    suspend fun getWorkoutById(id: String): WorkoutEntity?
    
    fun getWorkoutExercises(workoutId: String): Flow<List<com.cyberkidstudios.smartreps.core.domain.model.ActiveWorkoutExercise>>
    
    suspend fun saveWorkout(name: String, exercises: List<Exercise>)
    
    suspend fun startWorkoutLog(workoutId: String): String
    
    suspend fun logSet(workoutLogId: String, workoutExerciseId: String, setNumber: Int, reps: Int, weight: Float)
    
    suspend fun finishWorkoutLog(workoutLogId: String)
}
