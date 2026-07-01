package com.cyberkidstudios.smartreps.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cyberkidstudios.smartreps.core.data.local.entity.SetLogEntity
import com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutLogDao {
    @Query("SELECT * FROM workout_logs ORDER BY startedAt DESC")
    fun getAllWorkoutLogs(): Flow<List<WorkoutLogEntity>>

    @Query("SELECT * FROM workout_logs WHERE id = :id")
    suspend fun getWorkoutLogById(id: String): WorkoutLogEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutLog(log: WorkoutLogEntity)

    @Query("SELECT * FROM set_logs WHERE workoutLogId = :workoutLogId ORDER BY completedAt ASC")
    fun getSetsForWorkoutLog(workoutLogId: String): Flow<List<SetLogEntity>>

    @Query("SELECT * FROM set_logs WHERE workoutExerciseId = :workoutExerciseId ORDER BY completedAt DESC")
    fun getSetsHistoryForExercise(workoutExerciseId: String): Flow<List<SetLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetLog(setLog: SetLogEntity)
}
