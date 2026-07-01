package com.cyberkidstudios.smartreps.core.di

import com.cyberkidstudios.smartreps.core.data.local.dao.ExerciseDao
import com.cyberkidstudios.smartreps.core.domain.model.Exercise
import com.cyberkidstudios.smartreps.core.domain.repository.WorkoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

import com.cyberkidstudios.smartreps.core.data.local.dao.WorkoutDao
import com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutEntity
import com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutExerciseEntity
import java.util.UUID

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWorkoutRepository(
        exerciseDao: ExerciseDao,
        workoutDao: WorkoutDao,
        workoutLogDao: com.cyberkidstudios.smartreps.core.data.local.dao.WorkoutLogDao
    ): WorkoutRepository {
        return object : WorkoutRepository {
            override fun getAllExercises(): Flow<List<Exercise>> {
                return exerciseDao.getAllExercises().map { entities ->
                    entities.map { entity ->
                        Exercise(
                            id = entity.id,
                            namePt = entity.namePt,
                            nameEn = entity.nameEn,
                            muscleGroup = entity.muscleGroup,
                            equipmentType = entity.equipmentType,
                            gifLocalPath = entity.gifLocalPath,
                            instructionsPt = entity.instructionsPt,
                            instructionsEn = entity.instructionsEn,
                            defaultSets = entity.defaultSets,
                            defaultReps = entity.defaultReps,
                            defaultWeight = entity.defaultWeight,
                            defaultRestSeconds = entity.defaultRestSeconds
                        )
                    }
                }
            }

            override fun getAllWorkouts(): Flow<List<WorkoutEntity>> {
                return workoutDao.getAllWorkouts()
            }

            override suspend fun getWorkoutById(id: String): WorkoutEntity? {
                return workoutDao.getWorkoutById(id)
            }

            override fun getWorkoutExercises(workoutId: String): Flow<List<com.cyberkidstudios.smartreps.core.domain.model.ActiveWorkoutExercise>> {
                return workoutDao.getWorkoutExercisesWithDetails(workoutId).map { entities ->
                    entities.map { entity ->
                        com.cyberkidstudios.smartreps.core.domain.model.ActiveWorkoutExercise(
                            workoutExerciseId = entity.workoutExercise.id,
                            targetSets = entity.workoutExercise.targetSets,
                            targetReps = entity.workoutExercise.targetReps,
                            targetWeight = entity.workoutExercise.targetWeight,
                            restSeconds = entity.workoutExercise.restSeconds,
                            orderIndex = entity.workoutExercise.orderIndex,
                            exercise = Exercise(
                                id = entity.exercise.id,
                                namePt = entity.exercise.namePt,
                                nameEn = entity.exercise.nameEn,
                                muscleGroup = entity.exercise.muscleGroup,
                                equipmentType = entity.exercise.equipmentType,
                                gifLocalPath = entity.exercise.gifLocalPath,
                                instructionsPt = entity.exercise.instructionsPt,
                                instructionsEn = entity.exercise.instructionsEn,
                                defaultSets = entity.exercise.defaultSets,
                                defaultReps = entity.exercise.defaultReps,
                                defaultWeight = entity.exercise.defaultWeight,
                                defaultRestSeconds = entity.exercise.defaultRestSeconds
                            )
                        )
                    }
                }
            }

            override suspend fun saveWorkout(name: String, exercises: List<Exercise>) {
                val workoutId = UUID.randomUUID().toString()
                
                val workoutEntity = WorkoutEntity(
                    id = workoutId,
                    name = name,
                    description = "",
                    dayOfWeek = 0,
                    muscleGroups = exercises.map { it.muscleGroup }.distinct().joinToString(", "),
                    orderIndex = 0,
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
                
                workoutDao.insertWorkout(workoutEntity)
                
                val workoutExercises = exercises.mapIndexed { index, exercise ->
                    com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutExerciseEntity(
                        id = UUID.randomUUID().toString(),
                        workoutId = workoutId,
                        exerciseId = exercise.id,
                        orderIndex = index,
                        targetSets = exercise.defaultSets,
                        targetReps = exercise.defaultReps,
                        targetWeight = exercise.defaultWeight,
                        restSeconds = exercise.defaultRestSeconds,
                        notes = ""
                    )
                }
                
                workoutDao.insertWorkoutExercises(workoutExercises)
            }

            override suspend fun startWorkoutLog(workoutId: String): String {
                val logId = UUID.randomUUID().toString()
                val logEntity = com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutLogEntity(
                    id = logId,
                    workoutId = workoutId,
                    startedAt = System.currentTimeMillis(),
                    completedAt = null,
                    totalVolume = 0,
                    durationMinutes = 0,
                    status = "IN_PROGRESS",
                    source = "mobile"
                )
                workoutLogDao.insertWorkoutLog(logEntity)
                return logId
            }

            override suspend fun logSet(
                workoutLogId: String,
                workoutExerciseId: String,
                setNumber: Int,
                reps: Int,
                weight: Float
            ) {
                val setLog = com.cyberkidstudios.smartreps.core.data.local.entity.SetLogEntity(
                    id = UUID.randomUUID().toString(),
                    workoutLogId = workoutLogId,
                    workoutExerciseId = workoutExerciseId,
                    setNumber = setNumber,
                    repsCompleted = reps,
                    weightUsed = weight,
                    isWarmup = false,
                    isDropSet = false,
                    isFailure = false,
                    rpe = null,
                    completedAt = System.currentTimeMillis(),
                    source = "mobile"
                )
                workoutLogDao.insertSetLog(setLog)
            }

            override suspend fun finishWorkoutLog(workoutLogId: String) {
                val log = workoutLogDao.getWorkoutLogById(workoutLogId)
                if (log != null) {
                    val completedAt = System.currentTimeMillis()
                    val durationMinutes = ((completedAt - log.startedAt) / 60000).toInt()
                    // TODO: Calculate total volume from sets
                    val updatedLog = log.copy(
                        completedAt = completedAt,
                        status = "COMPLETED",
                        durationMinutes = durationMinutes
                    )
                    workoutLogDao.insertWorkoutLog(updatedLog)
                }
            }
        }
    }
}
