package com.cyberkidstudios.smartreps.core.`data`.local.dao

import androidx.collection.ArrayMap
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.room.util.recursiveFetchArrayMap
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import com.cyberkidstudios.smartreps.core.`data`.local.entity.ExerciseEntity
import com.cyberkidstudios.smartreps.core.`data`.local.entity.WorkoutEntity
import com.cyberkidstudios.smartreps.core.`data`.local.entity.WorkoutExerciseEntity
import com.cyberkidstudios.smartreps.core.`data`.local.entity.WorkoutExerciseWithExercise
import javax.`annotation`.processing.Generated
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class WorkoutDao_Impl(
  __db: RoomDatabase,
) : WorkoutDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfWorkoutEntity: EntityInsertAdapter<WorkoutEntity>

  private val __insertAdapterOfWorkoutExerciseEntity: EntityInsertAdapter<WorkoutExerciseEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfWorkoutEntity = object : EntityInsertAdapter<WorkoutEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `workouts` (`id`,`name`,`description`,`dayOfWeek`,`muscleGroups`,`orderIndex`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: WorkoutEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.description)
        statement.bindLong(4, entity.dayOfWeek.toLong())
        statement.bindText(5, entity.muscleGroups)
        statement.bindLong(6, entity.orderIndex.toLong())
        statement.bindLong(7, entity.createdAt)
        statement.bindLong(8, entity.updatedAt)
      }
    }
    this.__insertAdapterOfWorkoutExerciseEntity = object : EntityInsertAdapter<WorkoutExerciseEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `workout_exercises` (`id`,`workoutId`,`exerciseId`,`orderIndex`,`targetSets`,`targetReps`,`targetWeight`,`restSeconds`,`notes`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: WorkoutExerciseEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.workoutId)
        statement.bindText(3, entity.exerciseId)
        statement.bindLong(4, entity.orderIndex.toLong())
        statement.bindLong(5, entity.targetSets.toLong())
        statement.bindLong(6, entity.targetReps.toLong())
        statement.bindDouble(7, entity.targetWeight.toDouble())
        statement.bindLong(8, entity.restSeconds.toLong())
        statement.bindText(9, entity.notes)
      }
    }
  }

  public override suspend fun insertWorkout(workout: WorkoutEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfWorkoutEntity.insert(_connection, workout)
  }

  public override suspend fun insertWorkoutExercises(exercises: List<WorkoutExerciseEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfWorkoutExerciseEntity.insert(_connection, exercises)
  }

  public override fun getAllWorkouts(): Flow<List<WorkoutEntity>> {
    val _sql: String = "SELECT * FROM workouts ORDER BY dayOfWeek ASC, orderIndex ASC"
    return createFlow(__db, false, arrayOf("workouts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDayOfWeek: Int = getColumnIndexOrThrow(_stmt, "dayOfWeek")
        val _columnIndexOfMuscleGroups: Int = getColumnIndexOrThrow(_stmt, "muscleGroups")
        val _columnIndexOfOrderIndex: Int = getColumnIndexOrThrow(_stmt, "orderIndex")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: MutableList<WorkoutEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: WorkoutEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDayOfWeek: Int
          _tmpDayOfWeek = _stmt.getLong(_columnIndexOfDayOfWeek).toInt()
          val _tmpMuscleGroups: String
          _tmpMuscleGroups = _stmt.getText(_columnIndexOfMuscleGroups)
          val _tmpOrderIndex: Int
          _tmpOrderIndex = _stmt.getLong(_columnIndexOfOrderIndex).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = WorkoutEntity(_tmpId,_tmpName,_tmpDescription,_tmpDayOfWeek,_tmpMuscleGroups,_tmpOrderIndex,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getWorkoutById(id: String): WorkoutEntity? {
    val _sql: String = "SELECT * FROM workouts WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDayOfWeek: Int = getColumnIndexOrThrow(_stmt, "dayOfWeek")
        val _columnIndexOfMuscleGroups: Int = getColumnIndexOrThrow(_stmt, "muscleGroups")
        val _columnIndexOfOrderIndex: Int = getColumnIndexOrThrow(_stmt, "orderIndex")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: WorkoutEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDayOfWeek: Int
          _tmpDayOfWeek = _stmt.getLong(_columnIndexOfDayOfWeek).toInt()
          val _tmpMuscleGroups: String
          _tmpMuscleGroups = _stmt.getText(_columnIndexOfMuscleGroups)
          val _tmpOrderIndex: Int
          _tmpOrderIndex = _stmt.getLong(_columnIndexOfOrderIndex).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = WorkoutEntity(_tmpId,_tmpName,_tmpDescription,_tmpDayOfWeek,_tmpMuscleGroups,_tmpOrderIndex,_tmpCreatedAt,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getExercisesForWorkout(workoutId: String): Flow<List<WorkoutExerciseEntity>> {
    val _sql: String = "SELECT * FROM workout_exercises WHERE workoutId = ? ORDER BY orderIndex ASC"
    return createFlow(__db, false, arrayOf("workout_exercises")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, workoutId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfWorkoutId: Int = getColumnIndexOrThrow(_stmt, "workoutId")
        val _columnIndexOfExerciseId: Int = getColumnIndexOrThrow(_stmt, "exerciseId")
        val _columnIndexOfOrderIndex: Int = getColumnIndexOrThrow(_stmt, "orderIndex")
        val _columnIndexOfTargetSets: Int = getColumnIndexOrThrow(_stmt, "targetSets")
        val _columnIndexOfTargetReps: Int = getColumnIndexOrThrow(_stmt, "targetReps")
        val _columnIndexOfTargetWeight: Int = getColumnIndexOrThrow(_stmt, "targetWeight")
        val _columnIndexOfRestSeconds: Int = getColumnIndexOrThrow(_stmt, "restSeconds")
        val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
        val _result: MutableList<WorkoutExerciseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: WorkoutExerciseEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpWorkoutId: String
          _tmpWorkoutId = _stmt.getText(_columnIndexOfWorkoutId)
          val _tmpExerciseId: String
          _tmpExerciseId = _stmt.getText(_columnIndexOfExerciseId)
          val _tmpOrderIndex: Int
          _tmpOrderIndex = _stmt.getLong(_columnIndexOfOrderIndex).toInt()
          val _tmpTargetSets: Int
          _tmpTargetSets = _stmt.getLong(_columnIndexOfTargetSets).toInt()
          val _tmpTargetReps: Int
          _tmpTargetReps = _stmt.getLong(_columnIndexOfTargetReps).toInt()
          val _tmpTargetWeight: Float
          _tmpTargetWeight = _stmt.getDouble(_columnIndexOfTargetWeight).toFloat()
          val _tmpRestSeconds: Int
          _tmpRestSeconds = _stmt.getLong(_columnIndexOfRestSeconds).toInt()
          val _tmpNotes: String
          _tmpNotes = _stmt.getText(_columnIndexOfNotes)
          _item = WorkoutExerciseEntity(_tmpId,_tmpWorkoutId,_tmpExerciseId,_tmpOrderIndex,_tmpTargetSets,_tmpTargetReps,_tmpTargetWeight,_tmpRestSeconds,_tmpNotes)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getWorkoutExercisesWithDetails(workoutId: String): Flow<List<WorkoutExerciseWithExercise>> {
    val _sql: String = "SELECT * FROM workout_exercises WHERE workoutId = ? ORDER BY orderIndex ASC"
    return createFlow(__db, true, arrayOf("exercises", "workout_exercises")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, workoutId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfWorkoutId: Int = getColumnIndexOrThrow(_stmt, "workoutId")
        val _columnIndexOfExerciseId: Int = getColumnIndexOrThrow(_stmt, "exerciseId")
        val _columnIndexOfOrderIndex: Int = getColumnIndexOrThrow(_stmt, "orderIndex")
        val _columnIndexOfTargetSets: Int = getColumnIndexOrThrow(_stmt, "targetSets")
        val _columnIndexOfTargetReps: Int = getColumnIndexOrThrow(_stmt, "targetReps")
        val _columnIndexOfTargetWeight: Int = getColumnIndexOrThrow(_stmt, "targetWeight")
        val _columnIndexOfRestSeconds: Int = getColumnIndexOrThrow(_stmt, "restSeconds")
        val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
        val _collectionExercise: ArrayMap<String, ExerciseEntity?> = ArrayMap<String, ExerciseEntity?>()
        while (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfExerciseId)
          _collectionExercise.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipexercisesAscomCyberkidstudiosSmartrepsCoreDataLocalEntityExerciseEntity(_connection, _collectionExercise)
        val _result: MutableList<WorkoutExerciseWithExercise> = mutableListOf()
        while (_stmt.step()) {
          val _item: WorkoutExerciseWithExercise
          val _tmpWorkoutExercise: WorkoutExerciseEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpWorkoutId: String
          _tmpWorkoutId = _stmt.getText(_columnIndexOfWorkoutId)
          val _tmpExerciseId: String
          _tmpExerciseId = _stmt.getText(_columnIndexOfExerciseId)
          val _tmpOrderIndex: Int
          _tmpOrderIndex = _stmt.getLong(_columnIndexOfOrderIndex).toInt()
          val _tmpTargetSets: Int
          _tmpTargetSets = _stmt.getLong(_columnIndexOfTargetSets).toInt()
          val _tmpTargetReps: Int
          _tmpTargetReps = _stmt.getLong(_columnIndexOfTargetReps).toInt()
          val _tmpTargetWeight: Float
          _tmpTargetWeight = _stmt.getDouble(_columnIndexOfTargetWeight).toFloat()
          val _tmpRestSeconds: Int
          _tmpRestSeconds = _stmt.getLong(_columnIndexOfRestSeconds).toInt()
          val _tmpNotes: String
          _tmpNotes = _stmt.getText(_columnIndexOfNotes)
          _tmpWorkoutExercise = WorkoutExerciseEntity(_tmpId,_tmpWorkoutId,_tmpExerciseId,_tmpOrderIndex,_tmpTargetSets,_tmpTargetReps,_tmpTargetWeight,_tmpRestSeconds,_tmpNotes)
          val _tmpExercise: ExerciseEntity?
          val _tmpKey_1: String
          _tmpKey_1 = _stmt.getText(_columnIndexOfExerciseId)
          _tmpExercise = _collectionExercise.get(_tmpKey_1)
          if (_tmpExercise == null) {
            error("Relationship item 'exercise' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'exerciseId' and entityColumn named 'id'.")
          }
          _item = WorkoutExerciseWithExercise(_tmpWorkoutExercise,_tmpExercise)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteWorkout(id: String) {
    val _sql: String = "DELETE FROM workouts WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteExercisesForWorkout(workoutId: String) {
    val _sql: String = "DELETE FROM workout_exercises WHERE workoutId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, workoutId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __fetchRelationshipexercisesAscomCyberkidstudiosSmartrepsCoreDataLocalEntityExerciseEntity(_connection: SQLiteConnection, _map: ArrayMap<String, ExerciseEntity?>) {
    val __mapKeySet: Set<String> = _map.keys
    if (__mapKeySet.isEmpty()) {
      return
    }
    if (_map.size > 999) {
      recursiveFetchArrayMap(_map, false) { _tmpMap ->
        __fetchRelationshipexercisesAscomCyberkidstudiosSmartrepsCoreDataLocalEntityExerciseEntity(_connection, _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `id`,`namePt`,`nameEn`,`muscleGroup`,`equipmentType`,`gifLocalPath`,`instructionsPt`,`instructionsEn`,`defaultSets`,`defaultReps`,`defaultWeight`,`defaultRestSeconds` FROM `exercises` WHERE `id` IN (")
    val _inputSize: Int = __mapKeySet.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (_item: String in __mapKeySet) {
      _stmt.bindText(_argIndex, _item)
      _argIndex++
    }
    try {
      val _itemKeyIndex: Int = getColumnIndex(_stmt, "id")
      if (_itemKeyIndex == -1) {
        return
      }
      val _columnIndexOfId: Int = 0
      val _columnIndexOfNamePt: Int = 1
      val _columnIndexOfNameEn: Int = 2
      val _columnIndexOfMuscleGroup: Int = 3
      val _columnIndexOfEquipmentType: Int = 4
      val _columnIndexOfGifLocalPath: Int = 5
      val _columnIndexOfInstructionsPt: Int = 6
      val _columnIndexOfInstructionsEn: Int = 7
      val _columnIndexOfDefaultSets: Int = 8
      val _columnIndexOfDefaultReps: Int = 9
      val _columnIndexOfDefaultWeight: Int = 10
      val _columnIndexOfDefaultRestSeconds: Int = 11
      while (_stmt.step()) {
        val _tmpKey: String
        _tmpKey = _stmt.getText(_itemKeyIndex)
        if (_map.containsKey(_tmpKey)) {
          val _item_1: ExerciseEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpNamePt: String
          _tmpNamePt = _stmt.getText(_columnIndexOfNamePt)
          val _tmpNameEn: String
          _tmpNameEn = _stmt.getText(_columnIndexOfNameEn)
          val _tmpMuscleGroup: String
          _tmpMuscleGroup = _stmt.getText(_columnIndexOfMuscleGroup)
          val _tmpEquipmentType: String
          _tmpEquipmentType = _stmt.getText(_columnIndexOfEquipmentType)
          val _tmpGifLocalPath: String?
          if (_stmt.isNull(_columnIndexOfGifLocalPath)) {
            _tmpGifLocalPath = null
          } else {
            _tmpGifLocalPath = _stmt.getText(_columnIndexOfGifLocalPath)
          }
          val _tmpInstructionsPt: String
          _tmpInstructionsPt = _stmt.getText(_columnIndexOfInstructionsPt)
          val _tmpInstructionsEn: String
          _tmpInstructionsEn = _stmt.getText(_columnIndexOfInstructionsEn)
          val _tmpDefaultSets: Int
          _tmpDefaultSets = _stmt.getLong(_columnIndexOfDefaultSets).toInt()
          val _tmpDefaultReps: Int
          _tmpDefaultReps = _stmt.getLong(_columnIndexOfDefaultReps).toInt()
          val _tmpDefaultWeight: Float
          _tmpDefaultWeight = _stmt.getDouble(_columnIndexOfDefaultWeight).toFloat()
          val _tmpDefaultRestSeconds: Int
          _tmpDefaultRestSeconds = _stmt.getLong(_columnIndexOfDefaultRestSeconds).toInt()
          _item_1 = ExerciseEntity(_tmpId,_tmpNamePt,_tmpNameEn,_tmpMuscleGroup,_tmpEquipmentType,_tmpGifLocalPath,_tmpInstructionsPt,_tmpInstructionsEn,_tmpDefaultSets,_tmpDefaultReps,_tmpDefaultWeight,_tmpDefaultRestSeconds)
          _map.put(_tmpKey, _item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
