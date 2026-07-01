package com.cyberkidstudios.smartreps.core.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.cyberkidstudios.smartreps.core.`data`.local.entity.SetLogEntity
import com.cyberkidstudios.smartreps.core.`data`.local.entity.WorkoutLogEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class WorkoutLogDao_Impl(
  __db: RoomDatabase,
) : WorkoutLogDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfWorkoutLogEntity: EntityInsertAdapter<WorkoutLogEntity>

  private val __insertAdapterOfSetLogEntity: EntityInsertAdapter<SetLogEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfWorkoutLogEntity = object : EntityInsertAdapter<WorkoutLogEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `workout_logs` (`id`,`workoutId`,`startedAt`,`completedAt`,`totalVolume`,`durationMinutes`,`status`,`source`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: WorkoutLogEntity) {
        statement.bindText(1, entity.id)
        val _tmpWorkoutId: String? = entity.workoutId
        if (_tmpWorkoutId == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpWorkoutId)
        }
        statement.bindLong(3, entity.startedAt)
        val _tmpCompletedAt: Long? = entity.completedAt
        if (_tmpCompletedAt == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpCompletedAt)
        }
        statement.bindLong(5, entity.totalVolume.toLong())
        statement.bindLong(6, entity.durationMinutes.toLong())
        statement.bindText(7, entity.status)
        statement.bindText(8, entity.source)
      }
    }
    this.__insertAdapterOfSetLogEntity = object : EntityInsertAdapter<SetLogEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `set_logs` (`id`,`workoutLogId`,`workoutExerciseId`,`setNumber`,`repsCompleted`,`weightUsed`,`isWarmup`,`isDropSet`,`isFailure`,`rpe`,`completedAt`,`source`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SetLogEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.workoutLogId)
        val _tmpWorkoutExerciseId: String? = entity.workoutExerciseId
        if (_tmpWorkoutExerciseId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpWorkoutExerciseId)
        }
        statement.bindLong(4, entity.setNumber.toLong())
        statement.bindLong(5, entity.repsCompleted.toLong())
        statement.bindDouble(6, entity.weightUsed.toDouble())
        val _tmp: Int = if (entity.isWarmup) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.isDropSet) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isFailure) 1 else 0
        statement.bindLong(9, _tmp_2.toLong())
        val _tmpRpe: String? = entity.rpe
        if (_tmpRpe == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpRpe)
        }
        statement.bindLong(11, entity.completedAt)
        statement.bindText(12, entity.source)
      }
    }
  }

  public override suspend fun insertWorkoutLog(log: WorkoutLogEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfWorkoutLogEntity.insert(_connection, log)
  }

  public override suspend fun insertSetLog(setLog: SetLogEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfSetLogEntity.insert(_connection, setLog)
  }

  public override fun getAllWorkoutLogs(): Flow<List<WorkoutLogEntity>> {
    val _sql: String = "SELECT * FROM workout_logs ORDER BY startedAt DESC"
    return createFlow(__db, false, arrayOf("workout_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfWorkoutId: Int = getColumnIndexOrThrow(_stmt, "workoutId")
        val _columnIndexOfStartedAt: Int = getColumnIndexOrThrow(_stmt, "startedAt")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completedAt")
        val _columnIndexOfTotalVolume: Int = getColumnIndexOrThrow(_stmt, "totalVolume")
        val _columnIndexOfDurationMinutes: Int = getColumnIndexOrThrow(_stmt, "durationMinutes")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _result: MutableList<WorkoutLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: WorkoutLogEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpWorkoutId: String?
          if (_stmt.isNull(_columnIndexOfWorkoutId)) {
            _tmpWorkoutId = null
          } else {
            _tmpWorkoutId = _stmt.getText(_columnIndexOfWorkoutId)
          }
          val _tmpStartedAt: Long
          _tmpStartedAt = _stmt.getLong(_columnIndexOfStartedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          val _tmpTotalVolume: Int
          _tmpTotalVolume = _stmt.getLong(_columnIndexOfTotalVolume).toInt()
          val _tmpDurationMinutes: Int
          _tmpDurationMinutes = _stmt.getLong(_columnIndexOfDurationMinutes).toInt()
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSource: String
          _tmpSource = _stmt.getText(_columnIndexOfSource)
          _item = WorkoutLogEntity(_tmpId,_tmpWorkoutId,_tmpStartedAt,_tmpCompletedAt,_tmpTotalVolume,_tmpDurationMinutes,_tmpStatus,_tmpSource)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getWorkoutLogById(id: String): WorkoutLogEntity? {
    val _sql: String = "SELECT * FROM workout_logs WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfWorkoutId: Int = getColumnIndexOrThrow(_stmt, "workoutId")
        val _columnIndexOfStartedAt: Int = getColumnIndexOrThrow(_stmt, "startedAt")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completedAt")
        val _columnIndexOfTotalVolume: Int = getColumnIndexOrThrow(_stmt, "totalVolume")
        val _columnIndexOfDurationMinutes: Int = getColumnIndexOrThrow(_stmt, "durationMinutes")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _result: WorkoutLogEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpWorkoutId: String?
          if (_stmt.isNull(_columnIndexOfWorkoutId)) {
            _tmpWorkoutId = null
          } else {
            _tmpWorkoutId = _stmt.getText(_columnIndexOfWorkoutId)
          }
          val _tmpStartedAt: Long
          _tmpStartedAt = _stmt.getLong(_columnIndexOfStartedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          val _tmpTotalVolume: Int
          _tmpTotalVolume = _stmt.getLong(_columnIndexOfTotalVolume).toInt()
          val _tmpDurationMinutes: Int
          _tmpDurationMinutes = _stmt.getLong(_columnIndexOfDurationMinutes).toInt()
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSource: String
          _tmpSource = _stmt.getText(_columnIndexOfSource)
          _result = WorkoutLogEntity(_tmpId,_tmpWorkoutId,_tmpStartedAt,_tmpCompletedAt,_tmpTotalVolume,_tmpDurationMinutes,_tmpStatus,_tmpSource)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSetsForWorkoutLog(workoutLogId: String): Flow<List<SetLogEntity>> {
    val _sql: String = "SELECT * FROM set_logs WHERE workoutLogId = ? ORDER BY completedAt ASC"
    return createFlow(__db, false, arrayOf("set_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, workoutLogId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfWorkoutLogId: Int = getColumnIndexOrThrow(_stmt, "workoutLogId")
        val _columnIndexOfWorkoutExerciseId: Int = getColumnIndexOrThrow(_stmt, "workoutExerciseId")
        val _columnIndexOfSetNumber: Int = getColumnIndexOrThrow(_stmt, "setNumber")
        val _columnIndexOfRepsCompleted: Int = getColumnIndexOrThrow(_stmt, "repsCompleted")
        val _columnIndexOfWeightUsed: Int = getColumnIndexOrThrow(_stmt, "weightUsed")
        val _columnIndexOfIsWarmup: Int = getColumnIndexOrThrow(_stmt, "isWarmup")
        val _columnIndexOfIsDropSet: Int = getColumnIndexOrThrow(_stmt, "isDropSet")
        val _columnIndexOfIsFailure: Int = getColumnIndexOrThrow(_stmt, "isFailure")
        val _columnIndexOfRpe: Int = getColumnIndexOrThrow(_stmt, "rpe")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completedAt")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _result: MutableList<SetLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SetLogEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpWorkoutLogId: String
          _tmpWorkoutLogId = _stmt.getText(_columnIndexOfWorkoutLogId)
          val _tmpWorkoutExerciseId: String?
          if (_stmt.isNull(_columnIndexOfWorkoutExerciseId)) {
            _tmpWorkoutExerciseId = null
          } else {
            _tmpWorkoutExerciseId = _stmt.getText(_columnIndexOfWorkoutExerciseId)
          }
          val _tmpSetNumber: Int
          _tmpSetNumber = _stmt.getLong(_columnIndexOfSetNumber).toInt()
          val _tmpRepsCompleted: Int
          _tmpRepsCompleted = _stmt.getLong(_columnIndexOfRepsCompleted).toInt()
          val _tmpWeightUsed: Float
          _tmpWeightUsed = _stmt.getDouble(_columnIndexOfWeightUsed).toFloat()
          val _tmpIsWarmup: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsWarmup).toInt()
          _tmpIsWarmup = _tmp != 0
          val _tmpIsDropSet: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDropSet).toInt()
          _tmpIsDropSet = _tmp_1 != 0
          val _tmpIsFailure: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsFailure).toInt()
          _tmpIsFailure = _tmp_2 != 0
          val _tmpRpe: String?
          if (_stmt.isNull(_columnIndexOfRpe)) {
            _tmpRpe = null
          } else {
            _tmpRpe = _stmt.getText(_columnIndexOfRpe)
          }
          val _tmpCompletedAt: Long
          _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          val _tmpSource: String
          _tmpSource = _stmt.getText(_columnIndexOfSource)
          _item = SetLogEntity(_tmpId,_tmpWorkoutLogId,_tmpWorkoutExerciseId,_tmpSetNumber,_tmpRepsCompleted,_tmpWeightUsed,_tmpIsWarmup,_tmpIsDropSet,_tmpIsFailure,_tmpRpe,_tmpCompletedAt,_tmpSource)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSetsHistoryForExercise(workoutExerciseId: String): Flow<List<SetLogEntity>> {
    val _sql: String = "SELECT * FROM set_logs WHERE workoutExerciseId = ? ORDER BY completedAt DESC"
    return createFlow(__db, false, arrayOf("set_logs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, workoutExerciseId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfWorkoutLogId: Int = getColumnIndexOrThrow(_stmt, "workoutLogId")
        val _columnIndexOfWorkoutExerciseId: Int = getColumnIndexOrThrow(_stmt, "workoutExerciseId")
        val _columnIndexOfSetNumber: Int = getColumnIndexOrThrow(_stmt, "setNumber")
        val _columnIndexOfRepsCompleted: Int = getColumnIndexOrThrow(_stmt, "repsCompleted")
        val _columnIndexOfWeightUsed: Int = getColumnIndexOrThrow(_stmt, "weightUsed")
        val _columnIndexOfIsWarmup: Int = getColumnIndexOrThrow(_stmt, "isWarmup")
        val _columnIndexOfIsDropSet: Int = getColumnIndexOrThrow(_stmt, "isDropSet")
        val _columnIndexOfIsFailure: Int = getColumnIndexOrThrow(_stmt, "isFailure")
        val _columnIndexOfRpe: Int = getColumnIndexOrThrow(_stmt, "rpe")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completedAt")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _result: MutableList<SetLogEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SetLogEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpWorkoutLogId: String
          _tmpWorkoutLogId = _stmt.getText(_columnIndexOfWorkoutLogId)
          val _tmpWorkoutExerciseId: String?
          if (_stmt.isNull(_columnIndexOfWorkoutExerciseId)) {
            _tmpWorkoutExerciseId = null
          } else {
            _tmpWorkoutExerciseId = _stmt.getText(_columnIndexOfWorkoutExerciseId)
          }
          val _tmpSetNumber: Int
          _tmpSetNumber = _stmt.getLong(_columnIndexOfSetNumber).toInt()
          val _tmpRepsCompleted: Int
          _tmpRepsCompleted = _stmt.getLong(_columnIndexOfRepsCompleted).toInt()
          val _tmpWeightUsed: Float
          _tmpWeightUsed = _stmt.getDouble(_columnIndexOfWeightUsed).toFloat()
          val _tmpIsWarmup: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsWarmup).toInt()
          _tmpIsWarmup = _tmp != 0
          val _tmpIsDropSet: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDropSet).toInt()
          _tmpIsDropSet = _tmp_1 != 0
          val _tmpIsFailure: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsFailure).toInt()
          _tmpIsFailure = _tmp_2 != 0
          val _tmpRpe: String?
          if (_stmt.isNull(_columnIndexOfRpe)) {
            _tmpRpe = null
          } else {
            _tmpRpe = _stmt.getText(_columnIndexOfRpe)
          }
          val _tmpCompletedAt: Long
          _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          val _tmpSource: String
          _tmpSource = _stmt.getText(_columnIndexOfSource)
          _item = SetLogEntity(_tmpId,_tmpWorkoutLogId,_tmpWorkoutExerciseId,_tmpSetNumber,_tmpRepsCompleted,_tmpWeightUsed,_tmpIsWarmup,_tmpIsDropSet,_tmpIsFailure,_tmpRpe,_tmpCompletedAt,_tmpSource)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
