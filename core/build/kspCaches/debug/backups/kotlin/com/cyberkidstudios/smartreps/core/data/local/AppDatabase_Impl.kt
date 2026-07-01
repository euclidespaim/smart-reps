package com.cyberkidstudios.smartreps.core.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.cyberkidstudios.smartreps.core.`data`.local.dao.ExerciseDao
import com.cyberkidstudios.smartreps.core.`data`.local.dao.ExerciseDao_Impl
import com.cyberkidstudios.smartreps.core.`data`.local.dao.WorkoutDao
import com.cyberkidstudios.smartreps.core.`data`.local.dao.WorkoutDao_Impl
import com.cyberkidstudios.smartreps.core.`data`.local.dao.WorkoutLogDao
import com.cyberkidstudios.smartreps.core.`data`.local.dao.WorkoutLogDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _exerciseDao: Lazy<ExerciseDao> = lazy {
    ExerciseDao_Impl(this)
  }

  private val _workoutDao: Lazy<WorkoutDao> = lazy {
    WorkoutDao_Impl(this)
  }

  private val _workoutLogDao: Lazy<WorkoutLogDao> = lazy {
    WorkoutLogDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "6b27079836f4c83a232cf28733240c76", "48a6a39a8ebd8c196b3ea311ba42bfc0") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `exercises` (`id` TEXT NOT NULL, `namePt` TEXT NOT NULL, `nameEn` TEXT NOT NULL, `muscleGroup` TEXT NOT NULL, `equipmentType` TEXT NOT NULL, `gifLocalPath` TEXT, `instructionsPt` TEXT NOT NULL, `instructionsEn` TEXT NOT NULL, `defaultSets` INTEGER NOT NULL, `defaultReps` INTEGER NOT NULL, `defaultWeight` REAL NOT NULL, `defaultRestSeconds` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `workouts` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `dayOfWeek` INTEGER NOT NULL, `muscleGroups` TEXT NOT NULL, `orderIndex` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `workout_exercises` (`id` TEXT NOT NULL, `workoutId` TEXT NOT NULL, `exerciseId` TEXT NOT NULL, `orderIndex` INTEGER NOT NULL, `targetSets` INTEGER NOT NULL, `targetReps` INTEGER NOT NULL, `targetWeight` REAL NOT NULL, `restSeconds` INTEGER NOT NULL, `notes` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`workoutId`) REFERENCES `workouts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`exerciseId`) REFERENCES `exercises`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_exercises_workoutId` ON `workout_exercises` (`workoutId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_exercises_exerciseId` ON `workout_exercises` (`exerciseId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `workout_logs` (`id` TEXT NOT NULL, `workoutId` TEXT, `startedAt` INTEGER NOT NULL, `completedAt` INTEGER, `totalVolume` INTEGER NOT NULL, `durationMinutes` INTEGER NOT NULL, `status` TEXT NOT NULL, `source` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`workoutId`) REFERENCES `workouts`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_logs_workoutId` ON `workout_logs` (`workoutId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `set_logs` (`id` TEXT NOT NULL, `workoutLogId` TEXT NOT NULL, `workoutExerciseId` TEXT, `setNumber` INTEGER NOT NULL, `repsCompleted` INTEGER NOT NULL, `weightUsed` REAL NOT NULL, `isWarmup` INTEGER NOT NULL, `isDropSet` INTEGER NOT NULL, `isFailure` INTEGER NOT NULL, `rpe` TEXT, `completedAt` INTEGER NOT NULL, `source` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`workoutLogId`) REFERENCES `workout_logs`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`workoutExerciseId`) REFERENCES `workout_exercises`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_set_logs_workoutLogId` ON `set_logs` (`workoutLogId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_set_logs_workoutExerciseId` ON `set_logs` (`workoutExerciseId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `user_preferences` (`id` TEXT NOT NULL, `weightUnit` TEXT NOT NULL, `language` TEXT NOT NULL, `defaultRestSeconds` INTEGER NOT NULL, `isPremium` INTEGER NOT NULL, `premiumExpiresAt` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6b27079836f4c83a232cf28733240c76')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `exercises`")
        connection.execSQL("DROP TABLE IF EXISTS `workouts`")
        connection.execSQL("DROP TABLE IF EXISTS `workout_exercises`")
        connection.execSQL("DROP TABLE IF EXISTS `workout_logs`")
        connection.execSQL("DROP TABLE IF EXISTS `set_logs`")
        connection.execSQL("DROP TABLE IF EXISTS `user_preferences`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsExercises: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsExercises.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("namePt", TableInfo.Column("namePt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("nameEn", TableInfo.Column("nameEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("muscleGroup", TableInfo.Column("muscleGroup", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("equipmentType", TableInfo.Column("equipmentType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("gifLocalPath", TableInfo.Column("gifLocalPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("instructionsPt", TableInfo.Column("instructionsPt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("instructionsEn", TableInfo.Column("instructionsEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("defaultSets", TableInfo.Column("defaultSets", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("defaultReps", TableInfo.Column("defaultReps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("defaultWeight", TableInfo.Column("defaultWeight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExercises.put("defaultRestSeconds", TableInfo.Column("defaultRestSeconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysExercises: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesExercises: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoExercises: TableInfo = TableInfo("exercises", _columnsExercises, _foreignKeysExercises, _indicesExercises)
        val _existingExercises: TableInfo = read(connection, "exercises")
        if (!_infoExercises.equals(_existingExercises)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |exercises(com.cyberkidstudios.smartreps.core.data.local.entity.ExerciseEntity).
              | Expected:
              |""".trimMargin() + _infoExercises + """
              |
              | Found:
              |""".trimMargin() + _existingExercises)
        }
        val _columnsWorkouts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsWorkouts.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkouts.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkouts.put("description", TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkouts.put("dayOfWeek", TableInfo.Column("dayOfWeek", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkouts.put("muscleGroups", TableInfo.Column("muscleGroups", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkouts.put("orderIndex", TableInfo.Column("orderIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkouts.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkouts.put("updatedAt", TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysWorkouts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesWorkouts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoWorkouts: TableInfo = TableInfo("workouts", _columnsWorkouts, _foreignKeysWorkouts, _indicesWorkouts)
        val _existingWorkouts: TableInfo = read(connection, "workouts")
        if (!_infoWorkouts.equals(_existingWorkouts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |workouts(com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutEntity).
              | Expected:
              |""".trimMargin() + _infoWorkouts + """
              |
              | Found:
              |""".trimMargin() + _existingWorkouts)
        }
        val _columnsWorkoutExercises: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsWorkoutExercises.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutExercises.put("workoutId", TableInfo.Column("workoutId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutExercises.put("exerciseId", TableInfo.Column("exerciseId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutExercises.put("orderIndex", TableInfo.Column("orderIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutExercises.put("targetSets", TableInfo.Column("targetSets", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutExercises.put("targetReps", TableInfo.Column("targetReps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutExercises.put("targetWeight", TableInfo.Column("targetWeight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutExercises.put("restSeconds", TableInfo.Column("restSeconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutExercises.put("notes", TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysWorkoutExercises: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysWorkoutExercises.add(TableInfo.ForeignKey("workouts", "CASCADE", "NO ACTION", listOf("workoutId"), listOf("id")))
        _foreignKeysWorkoutExercises.add(TableInfo.ForeignKey("exercises", "CASCADE", "NO ACTION", listOf("exerciseId"), listOf("id")))
        val _indicesWorkoutExercises: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesWorkoutExercises.add(TableInfo.Index("index_workout_exercises_workoutId", false, listOf("workoutId"), listOf("ASC")))
        _indicesWorkoutExercises.add(TableInfo.Index("index_workout_exercises_exerciseId", false, listOf("exerciseId"), listOf("ASC")))
        val _infoWorkoutExercises: TableInfo = TableInfo("workout_exercises", _columnsWorkoutExercises, _foreignKeysWorkoutExercises, _indicesWorkoutExercises)
        val _existingWorkoutExercises: TableInfo = read(connection, "workout_exercises")
        if (!_infoWorkoutExercises.equals(_existingWorkoutExercises)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |workout_exercises(com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutExerciseEntity).
              | Expected:
              |""".trimMargin() + _infoWorkoutExercises + """
              |
              | Found:
              |""".trimMargin() + _existingWorkoutExercises)
        }
        val _columnsWorkoutLogs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsWorkoutLogs.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutLogs.put("workoutId", TableInfo.Column("workoutId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutLogs.put("startedAt", TableInfo.Column("startedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutLogs.put("completedAt", TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutLogs.put("totalVolume", TableInfo.Column("totalVolume", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutLogs.put("durationMinutes", TableInfo.Column("durationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutLogs.put("status", TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWorkoutLogs.put("source", TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysWorkoutLogs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysWorkoutLogs.add(TableInfo.ForeignKey("workouts", "SET NULL", "NO ACTION", listOf("workoutId"), listOf("id")))
        val _indicesWorkoutLogs: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesWorkoutLogs.add(TableInfo.Index("index_workout_logs_workoutId", false, listOf("workoutId"), listOf("ASC")))
        val _infoWorkoutLogs: TableInfo = TableInfo("workout_logs", _columnsWorkoutLogs, _foreignKeysWorkoutLogs, _indicesWorkoutLogs)
        val _existingWorkoutLogs: TableInfo = read(connection, "workout_logs")
        if (!_infoWorkoutLogs.equals(_existingWorkoutLogs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |workout_logs(com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutLogEntity).
              | Expected:
              |""".trimMargin() + _infoWorkoutLogs + """
              |
              | Found:
              |""".trimMargin() + _existingWorkoutLogs)
        }
        val _columnsSetLogs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSetLogs.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("workoutLogId", TableInfo.Column("workoutLogId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("workoutExerciseId", TableInfo.Column("workoutExerciseId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("setNumber", TableInfo.Column("setNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("repsCompleted", TableInfo.Column("repsCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("weightUsed", TableInfo.Column("weightUsed", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("isWarmup", TableInfo.Column("isWarmup", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("isDropSet", TableInfo.Column("isDropSet", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("isFailure", TableInfo.Column("isFailure", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("rpe", TableInfo.Column("rpe", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("completedAt", TableInfo.Column("completedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSetLogs.put("source", TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSetLogs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysSetLogs.add(TableInfo.ForeignKey("workout_logs", "CASCADE", "NO ACTION", listOf("workoutLogId"), listOf("id")))
        _foreignKeysSetLogs.add(TableInfo.ForeignKey("workout_exercises", "SET NULL", "NO ACTION", listOf("workoutExerciseId"), listOf("id")))
        val _indicesSetLogs: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSetLogs.add(TableInfo.Index("index_set_logs_workoutLogId", false, listOf("workoutLogId"), listOf("ASC")))
        _indicesSetLogs.add(TableInfo.Index("index_set_logs_workoutExerciseId", false, listOf("workoutExerciseId"), listOf("ASC")))
        val _infoSetLogs: TableInfo = TableInfo("set_logs", _columnsSetLogs, _foreignKeysSetLogs, _indicesSetLogs)
        val _existingSetLogs: TableInfo = read(connection, "set_logs")
        if (!_infoSetLogs.equals(_existingSetLogs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |set_logs(com.cyberkidstudios.smartreps.core.data.local.entity.SetLogEntity).
              | Expected:
              |""".trimMargin() + _infoSetLogs + """
              |
              | Found:
              |""".trimMargin() + _existingSetLogs)
        }
        val _columnsUserPreferences: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUserPreferences.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserPreferences.put("weightUnit", TableInfo.Column("weightUnit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserPreferences.put("language", TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserPreferences.put("defaultRestSeconds", TableInfo.Column("defaultRestSeconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserPreferences.put("isPremium", TableInfo.Column("isPremium", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserPreferences.put("premiumExpiresAt", TableInfo.Column("premiumExpiresAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUserPreferences: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUserPreferences: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUserPreferences: TableInfo = TableInfo("user_preferences", _columnsUserPreferences, _foreignKeysUserPreferences, _indicesUserPreferences)
        val _existingUserPreferences: TableInfo = read(connection, "user_preferences")
        if (!_infoUserPreferences.equals(_existingUserPreferences)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |user_preferences(com.cyberkidstudios.smartreps.core.data.local.entity.UserPreferencesEntity).
              | Expected:
              |""".trimMargin() + _infoUserPreferences + """
              |
              | Found:
              |""".trimMargin() + _existingUserPreferences)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "exercises", "workouts", "workout_exercises", "workout_logs", "set_logs", "user_preferences")
  }

  public override fun clearAllTables() {
    super.performClear(true, "exercises", "workouts", "workout_exercises", "workout_logs", "set_logs", "user_preferences")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ExerciseDao::class, ExerciseDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(WorkoutDao::class, WorkoutDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(WorkoutLogDao::class, WorkoutLogDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun exerciseDao(): ExerciseDao = _exerciseDao.value

  public override fun workoutDao(): WorkoutDao = _workoutDao.value

  public override fun workoutLogDao(): WorkoutLogDao = _workoutLogDao.value
}
