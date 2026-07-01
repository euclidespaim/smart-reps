package com.cyberkidstudios.smartreps.ui.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.cyberkidstudios.smartreps.ui.components.SmartRepsButton
import com.cyberkidstudios.smartreps.ui.components.SmartRepsCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveWorkoutScreen(
    viewModel: ActiveWorkoutViewModel,
    onNavigateBack: () -> Unit
) {
    val workout by viewModel.workout.collectAsState()
    val exercises by viewModel.exercises.collectAsState()
    var showTimer by remember { mutableStateOf(false) }
    var currentRestSeconds by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(workout?.name ?: "Carregando...", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    Button(
                        onClick = onNavigateBack,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text("< Voltar", color = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(exercises) { exercise ->
                    SmartRepsCard(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { /* TODO: Interação durante execução */ }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = exercise.exercise.gifLocalPath,
                                    contentDescription = exercise.exercise.nameEn,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.White),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = exercise.exercise.nameEn.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Alvo: ${exercise.targetSets} Séries x ${exercise.targetReps} Reps",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            // Set Rows
                            for (i in 1..exercise.targetSets) {
                                var weightInput by remember { mutableStateOf(exercise.targetWeight.toString()) }
                                var repsInput by remember { mutableStateOf(exercise.targetReps.toString()) }
                                var isCompleted by remember { mutableStateOf(false) }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .background(
                                            if (isCompleted) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) 
                                            else Color.Transparent,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .padding(4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "$i",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.width(24.dp)
                                    )
                                    
                                    OutlinedTextField(
                                        value = weightInput,
                                        onValueChange = { weightInput = it },
                                        modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                                        label = { Text("kg") },
                                        singleLine = true,
                                        enabled = !isCompleted,
                                        textStyle = MaterialTheme.typography.bodyLarge
                                    )
                                    
                                    OutlinedTextField(
                                        value = repsInput,
                                        onValueChange = { repsInput = it },
                                        modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                                        label = { Text("reps") },
                                        singleLine = true,
                                        enabled = !isCompleted,
                                        textStyle = MaterialTheme.typography.bodyLarge
                                    )
                                    
                                    IconButton(
                                        onClick = {
                                            if (!isCompleted) {
                                                val weight = weightInput.toFloatOrNull() ?: 0f
                                                val reps = repsInput.toIntOrNull() ?: 0
                                                viewModel.logSet(exercise.workoutExerciseId, i, reps, weight)
                                                isCompleted = true
                                                currentRestSeconds = exercise.restSeconds
                                                showTimer = true
                                            }
                                        }
                                    ) {
                                        Text(
                                            text = "✓",
                                            color = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background,
                shadowElevation = 8.dp
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    SmartRepsButton(
                        text = "Finalizar Treino",
                        onClick = { 
                            viewModel.finishWorkout()
                            onNavigateBack() 
                        }
                    )
                }
            }
        }
    }


    if (showTimer) {
        ModalBottomSheet(
            onDismissRequest = { showTimer = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            var timeLeft by remember { mutableStateOf(currentRestSeconds) }
            
            LaunchedEffect(showTimer) {
                while (timeLeft > 0) {
                    kotlinx.coroutines.delay(1000L)
                    timeLeft--
                }
                if (timeLeft == 0) {
                    showTimer = false // Auto close when done
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Descanso",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                SmartRepsButton(
                    text = "Pular Descanso",
                    onClick = { showTimer = false }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
