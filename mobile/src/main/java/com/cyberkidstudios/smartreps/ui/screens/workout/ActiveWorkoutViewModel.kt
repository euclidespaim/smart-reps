package com.cyberkidstudios.smartreps.ui.screens.workout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberkidstudios.smartreps.core.data.local.entity.WorkoutEntity
import com.cyberkidstudios.smartreps.core.domain.model.Exercise
import com.cyberkidstudios.smartreps.core.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

import kotlinx.coroutines.Dispatchers

@HiltViewModel
class ActiveWorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val workoutId: String = checkNotNull(savedStateHandle["workoutId"])
    private var workoutLogId: String? = null

    private val _workout = MutableStateFlow<WorkoutEntity?>(null)
    val workout: StateFlow<WorkoutEntity?> = _workout.asStateFlow()

    private val _exercises = MutableStateFlow<List<com.cyberkidstudios.smartreps.core.domain.model.ActiveWorkoutExercise>>(emptyList())
    val exercises: StateFlow<List<com.cyberkidstudios.smartreps.core.domain.model.ActiveWorkoutExercise>> = _exercises.asStateFlow()

    init {
        loadWorkoutData()
    }

    private fun loadWorkoutData() {
        viewModelScope.launch(Dispatchers.IO) {
            _workout.value = workoutRepository.getWorkoutById(workoutId)
            workoutLogId = workoutRepository.startWorkoutLog(workoutId)
            
            workoutRepository.getWorkoutExercises(workoutId).collect {
                _exercises.value = it
            }
        }
    }

    fun logSet(workoutExerciseId: String, setNumber: Int, reps: Int, weight: Float) {
        val logId = workoutLogId ?: return
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.logSet(logId, workoutExerciseId, setNumber, reps, weight)
        }
    }

    fun finishWorkout() {
        val logId = workoutLogId ?: return
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.finishWorkoutLog(logId)
        }
    }
}
