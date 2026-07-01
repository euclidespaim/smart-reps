package com.cyberkidstudios.smartreps.core.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.cyberkidstudios.smartreps.core.`data`.local.entity.ExerciseEntity
import javax.`annotation`.processing.Generated
import kotlin.Float
import kotlin.Int
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
public class ExerciseDao_Impl(
  __db: RoomDatabase,
) : ExerciseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfExerciseEntity: EntityInsertAdapter<ExerciseEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfExerciseEntity = object : EntityInsertAdapter<ExerciseEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `exercises` (`id`,`namePt`,`nameEn`,`muscleGroup`,`equipmentType`,`gifLocalPath`,`instructionsPt`,`instructionsEn`,`defaultSets`,`defaultReps`,`defaultWeight`,`defaultRestSeconds`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ExerciseEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.namePt)
        statement.bindText(3, entity.nameEn)
        statement.bindText(4, entity.muscleGroup)
        statement.bindText(5, entity.equipmentType)
        val _tmpGifLocalPath: String? = entity.gifLocalPath
        if (_tmpGifLocalPath == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpGifLocalPath)
        }
        statement.bindText(7, entity.instructionsPt)
        statement.bindText(8, entity.instructionsEn)
        statement.bindLong(9, entity.defaultSets.toLong())
        statement.bindLong(10, entity.defaultReps.toLong())
        statement.bindDouble(11, entity.defaultWeight.toDouble())
        statement.bindLong(12, entity.defaultRestSeconds.toLong())
      }
    }
  }

  public override suspend fun insertExercises(exercises: List<ExerciseEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfExerciseEntity.insert(_connection, exercises)
  }

  public override suspend fun insertExercise(exercise: ExerciseEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfExerciseEntity.insert(_connection, exercise)
  }

  public override fun getAllExercises(): Flow<List<ExerciseEntity>> {
    val _sql: String = "SELECT * FROM exercises"
    return createFlow(__db, false, arrayOf("exercises")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNamePt: Int = getColumnIndexOrThrow(_stmt, "namePt")
        val _columnIndexOfNameEn: Int = getColumnIndexOrThrow(_stmt, "nameEn")
        val _columnIndexOfMuscleGroup: Int = getColumnIndexOrThrow(_stmt, "muscleGroup")
        val _columnIndexOfEquipmentType: Int = getColumnIndexOrThrow(_stmt, "equipmentType")
        val _columnIndexOfGifLocalPath: Int = getColumnIndexOrThrow(_stmt, "gifLocalPath")
        val _columnIndexOfInstructionsPt: Int = getColumnIndexOrThrow(_stmt, "instructionsPt")
        val _columnIndexOfInstructionsEn: Int = getColumnIndexOrThrow(_stmt, "instructionsEn")
        val _columnIndexOfDefaultSets: Int = getColumnIndexOrThrow(_stmt, "defaultSets")
        val _columnIndexOfDefaultReps: Int = getColumnIndexOrThrow(_stmt, "defaultReps")
        val _columnIndexOfDefaultWeight: Int = getColumnIndexOrThrow(_stmt, "defaultWeight")
        val _columnIndexOfDefaultRestSeconds: Int = getColumnIndexOrThrow(_stmt, "defaultRestSeconds")
        val _result: MutableList<ExerciseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExerciseEntity
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
          _item = ExerciseEntity(_tmpId,_tmpNamePt,_tmpNameEn,_tmpMuscleGroup,_tmpEquipmentType,_tmpGifLocalPath,_tmpInstructionsPt,_tmpInstructionsEn,_tmpDefaultSets,_tmpDefaultReps,_tmpDefaultWeight,_tmpDefaultRestSeconds)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getExerciseById(id: String): ExerciseEntity? {
    val _sql: String = "SELECT * FROM exercises WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNamePt: Int = getColumnIndexOrThrow(_stmt, "namePt")
        val _columnIndexOfNameEn: Int = getColumnIndexOrThrow(_stmt, "nameEn")
        val _columnIndexOfMuscleGroup: Int = getColumnIndexOrThrow(_stmt, "muscleGroup")
        val _columnIndexOfEquipmentType: Int = getColumnIndexOrThrow(_stmt, "equipmentType")
        val _columnIndexOfGifLocalPath: Int = getColumnIndexOrThrow(_stmt, "gifLocalPath")
        val _columnIndexOfInstructionsPt: Int = getColumnIndexOrThrow(_stmt, "instructionsPt")
        val _columnIndexOfInstructionsEn: Int = getColumnIndexOrThrow(_stmt, "instructionsEn")
        val _columnIndexOfDefaultSets: Int = getColumnIndexOrThrow(_stmt, "defaultSets")
        val _columnIndexOfDefaultReps: Int = getColumnIndexOrThrow(_stmt, "defaultReps")
        val _columnIndexOfDefaultWeight: Int = getColumnIndexOrThrow(_stmt, "defaultWeight")
        val _columnIndexOfDefaultRestSeconds: Int = getColumnIndexOrThrow(_stmt, "defaultRestSeconds")
        val _result: ExerciseEntity?
        if (_stmt.step()) {
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
          _result = ExerciseEntity(_tmpId,_tmpNamePt,_tmpNameEn,_tmpMuscleGroup,_tmpEquipmentType,_tmpGifLocalPath,_tmpInstructionsPt,_tmpInstructionsEn,_tmpDefaultSets,_tmpDefaultReps,_tmpDefaultWeight,_tmpDefaultRestSeconds)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getExerciseCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM exercises"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getExercisesByMuscleGroup(muscleGroup: String): Flow<List<ExerciseEntity>> {
    val _sql: String = "SELECT * FROM exercises WHERE muscleGroup = ?"
    return createFlow(__db, false, arrayOf("exercises")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, muscleGroup)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNamePt: Int = getColumnIndexOrThrow(_stmt, "namePt")
        val _columnIndexOfNameEn: Int = getColumnIndexOrThrow(_stmt, "nameEn")
        val _columnIndexOfMuscleGroup: Int = getColumnIndexOrThrow(_stmt, "muscleGroup")
        val _columnIndexOfEquipmentType: Int = getColumnIndexOrThrow(_stmt, "equipmentType")
        val _columnIndexOfGifLocalPath: Int = getColumnIndexOrThrow(_stmt, "gifLocalPath")
        val _columnIndexOfInstructionsPt: Int = getColumnIndexOrThrow(_stmt, "instructionsPt")
        val _columnIndexOfInstructionsEn: Int = getColumnIndexOrThrow(_stmt, "instructionsEn")
        val _columnIndexOfDefaultSets: Int = getColumnIndexOrThrow(_stmt, "defaultSets")
        val _columnIndexOfDefaultReps: Int = getColumnIndexOrThrow(_stmt, "defaultReps")
        val _columnIndexOfDefaultWeight: Int = getColumnIndexOrThrow(_stmt, "defaultWeight")
        val _columnIndexOfDefaultRestSeconds: Int = getColumnIndexOrThrow(_stmt, "defaultRestSeconds")
        val _result: MutableList<ExerciseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExerciseEntity
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
          _item = ExerciseEntity(_tmpId,_tmpNamePt,_tmpNameEn,_tmpMuscleGroup,_tmpEquipmentType,_tmpGifLocalPath,_tmpInstructionsPt,_tmpInstructionsEn,_tmpDefaultSets,_tmpDefaultReps,_tmpDefaultWeight,_tmpDefaultRestSeconds)
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
