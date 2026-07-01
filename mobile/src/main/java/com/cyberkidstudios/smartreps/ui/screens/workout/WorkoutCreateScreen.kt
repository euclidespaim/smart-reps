package com.cyberkidstudios.smartreps.ui.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.cyberkidstudios.smartreps.ui.components.SmartRepsButton
import com.cyberkidstudios.smartreps.ui.components.SmartRepsTextField

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.cyberkidstudios.smartreps.ui.components.SmartRepsCard
import coil3.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutCreateScreen(
    viewModel: WorkoutViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToExercises: () -> Unit
) {
    val workoutName by viewModel.workoutName.collectAsState()
    val selectedExercises by viewModel.selectedExercises.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novo Treino", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    Button(
                        onClick = onNavigateBack,
                        colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
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
                .padding(16.dp)
        ) {
            SmartRepsTextField(
                value = workoutName,
                onValueChange = { viewModel.setWorkoutName(it) },
                label = "Nome da Ficha (Ex: Push Day)"
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Exercícios",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${selectedExercises.size} selecionados",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = onNavigateToExercises,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("+ Adicionar Exercício")
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(selectedExercises) { exercise ->
                    SmartRepsCard(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { viewModel.toggleExerciseSelection(exercise) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = exercise.gifLocalPath,
                                contentDescription = exercise.nameEn,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(androidx.compose.ui.graphics.Color.White),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = exercise.nameEn.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${exercise.muscleGroup.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }} • ${exercise.equipmentType.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = "X",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SmartRepsButton(
                text = "Salvar Treino",
                onClick = { 
                    viewModel.saveWorkout()
                    onNavigateBack() 
                },
                enabled = workoutName.isNotBlank() && selectedExercises.isNotEmpty()
            )
        }
    }
}
