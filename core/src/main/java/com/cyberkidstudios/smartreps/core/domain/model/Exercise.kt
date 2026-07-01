package com.cyberkidstudios.smartreps.core.domain.model

data class Exercise(
    val id: String,
    val namePt: String,
    val nameEn: String,
    val muscleGroup: String,
    val equipmentType: String,
    val gifLocalPath: String?,
    val instructionsPt: String,
    val instructionsEn: String,
    val defaultSets: Int,
    val defaultReps: Int,
    val defaultWeight: Float,
    val defaultRestSeconds: Int
)
