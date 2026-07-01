package com.cyberkidstudios.smartreps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cyberkidstudios.smartreps.ui.theme.SmartRepsTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cyberkidstudios.smartreps.ui.screens.home.HomeScreen
import com.cyberkidstudios.smartreps.ui.screens.workout.WorkoutCreateScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.navigation
import com.cyberkidstudios.smartreps.ui.screens.workout.WorkoutViewModel
import com.cyberkidstudios.smartreps.ui.screens.workout.ExerciseSelectionScreen
import com.cyberkidstudios.smartreps.core.data.local.DatabaseSeeder
import com.cyberkidstudios.smartreps.ui.screens.onboarding.OnboardingScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var databaseSeeder: DatabaseSeeder

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        
        setContent {
            SmartRepsTheme {
                androidx.compose.material3.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    val navController = rememberNavController()

                    LaunchedEffect(Unit) {
                        scope.launch(Dispatchers.IO) {
                            try {
                                databaseSeeder.seedExercisesIfEmpty()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }

                    NavHost(navController = navController, startDestination = "onboarding") {
                        composable("onboarding") {
                            OnboardingScreen(
                                onComplete = {
                                    navController.navigate("home") {
                                        popUpTo("onboarding") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("home") {
                            HomeScreen(
                                onNavigateToCreate = {
                                    navController.navigate("workout_create")
                                },
                                onNavigateToActiveWorkout = { workoutId ->
                                    navController.navigate("active_workout/$workoutId")
                                }
                            )
                        }
                        
                        composable(
                            route = "active_workout/{workoutId}",
                            arguments = listOf(androidx.navigation.navArgument("workoutId") { type = androidx.navigation.NavType.StringType })
                        ) { backStackEntry ->
                            val viewModel: com.cyberkidstudios.smartreps.ui.screens.workout.ActiveWorkoutViewModel = hiltViewModel()
                            com.cyberkidstudios.smartreps.ui.screens.workout.ActiveWorkoutScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                        
                        // Nested Graph para criação de Treino (compartilha a ViewModel)
                        navigation(startDestination = "workout_create_form", route = "workout_create") {
                            composable("workout_create_form") { backStackEntry ->
                                val parentEntry = remember(backStackEntry) {
                                    navController.getBackStackEntry("workout_create")
                                }
                                val viewModel: WorkoutViewModel = hiltViewModel(parentEntry)
                                WorkoutCreateScreen(
                                    viewModel = viewModel,
                                    onNavigateBack = { navController.popBackStack() },
                                    onNavigateToExercises = { navController.navigate("exercise_selection") }
                                )
                            }
                            composable("exercise_selection") { backStackEntry ->
                                val parentEntry = remember(backStackEntry) {
                                    navController.getBackStackEntry("workout_create")
                                }
                                val viewModel: WorkoutViewModel = hiltViewModel(parentEntry)
                                ExerciseSelectionScreen(
                                    viewModel = viewModel,
                                    onNavigateBack = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
