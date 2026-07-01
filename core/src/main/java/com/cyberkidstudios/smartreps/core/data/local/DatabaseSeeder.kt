package com.cyberkidstudios.smartreps.core.data.local

import android.content.Context
import com.cyberkidstudios.smartreps.core.R
import com.cyberkidstudios.smartreps.core.data.local.dao.ExerciseDao
import com.cyberkidstudios.smartreps.core.data.local.entity.ExerciseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

class DatabaseSeeder(
    private val context: Context,
    private val exerciseDao: ExerciseDao
) {
    suspend fun seedExercisesIfEmpty() {
        withContext(Dispatchers.IO) {
            val count = exerciseDao.getExerciseCount()
            if (count == 0) {
                try {
                    val inputStream = context.resources.openRawResource(R.raw.exercises_seed)
                    val jsonString = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
                    val jsonArray = JSONArray(jsonString)
                    
                    val exercisesToInsert = mutableListOf<ExerciseEntity>()
                    
                    for (i in 0 until jsonArray.length()) {
                        val jsonObj = jsonArray.getJSONObject(i)
                        
                        val instructionsArray = jsonObj.optJSONArray("instructions")
                        val instructionsList = mutableListOf<String>()
                        if (instructionsArray != null) {
                            for (j in 0 until instructionsArray.length()) {
                                instructionsList.add(instructionsArray.getString(j))
                            }
                        }
                        val instructionsStr = instructionsList.joinToString("\n")
                        
                        val targetMusclesArray = jsonObj.optJSONArray("targetMuscles")
                        val muscleGroup = if (targetMusclesArray != null && targetMusclesArray.length() > 0) {
                            targetMusclesArray.getString(0)
                        } else "Other"
                        
                        val equipmentsArray = jsonObj.optJSONArray("equipments")
                        val equipmentType = if (equipmentsArray != null && equipmentsArray.length() > 0) {
                            equipmentsArray.getString(0)
                        } else "Body weight"

                        exercisesToInsert.add(
                            ExerciseEntity(
                                id = jsonObj.getString("exerciseId"),
                                namePt = jsonObj.getString("name"), // Placeholder for PT until translated
                                nameEn = jsonObj.getString("name"),
                                muscleGroup = muscleGroup,
                                equipmentType = equipmentType,
                                gifLocalPath = jsonObj.optString("gifUrl"),
                                instructionsPt = instructionsStr,
                                instructionsEn = instructionsStr,
                                defaultSets = 3,
                                defaultReps = 10,
                                defaultWeight = 0f,
                                defaultRestSeconds = 60
                            )
                        )
                    }
                    
                    exerciseDao.insertExercises(exercisesToInsert)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
