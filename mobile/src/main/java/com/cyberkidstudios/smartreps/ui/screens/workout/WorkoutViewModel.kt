package com.cyberkidstudios.smartreps.ui.screens.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberkidstudios.smartreps.core.domain.model.Exercise
import com.cyberkidstudios.smartreps.core.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {

    private val _workoutName = MutableStateFlow("")
    val workoutName: StateFlow<String> = _workoutName.asStateFlow()

    private val _selectedExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val selectedExercises: StateFlow<List<Exercise>> = _selectedExercises.asStateFlow()

    private val _allExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val allExercises: StateFlow<List<Exercise>> = _allExercises.asStateFlow()

    init {
        loadAllExercises()
    }

    private fun loadAllExercises() {
        viewModelScope.launch {
            workoutRepository.getAllExercises().collect { exercises ->
                _allExercises.value = exercises
            }
        }
    }

    fun setWorkoutName(name: String) {
        _workoutName.value = name
    }

    fun toggleExerciseSelection(exercise: Exercise) {
        val currentList = _selectedExercises.value.toMutableList()
        val existing = currentList.find { it.id == exercise.id }
        if (existing != null) {
            currentList.remove(existing)
        } else {
            currentList.add(exercise)
        }
        _selectedExercises.value = currentList
    }

    fun saveWorkout() {
        val name = _workoutName.value
        val exercises = _selectedExercises.value
        
        if (name.isNotBlank() && exercises.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                workoutRepository.saveWorkout(name, exercises)
                // TODO: Adicionar UI State para sucesso/navegação
            }
        }
    }
}
