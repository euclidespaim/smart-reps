package com.cyberkidstudios.smartreps.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cyberkidstudios.smartreps.core.domain.repository.WorkoutRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workoutRepository: WorkoutRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        
        setContent {
            val scope = rememberCoroutineScope()
            var dbStatus by remember { mutableStateOf("Verificando...") }

            LaunchedEffect(Unit) {
                scope.launch(Dispatchers.IO) {
                    try {
                        workoutRepository.getAllExercises().collect { 
                            dbStatus = "✅ DB OK! Hilt OK! Repo OK!"
                        }
                    } catch (e: Exception) {
                        dbStatus = "❌ Erro DB/Repo"
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "SmartReps Wear")
                Text(text = dbStatus)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { 
                    throw RuntimeException("Test Crash - SmartReps Wear Phase 1") 
                }) {
                    Text("Forçar Crash")
                }
            }
        }
    }
}
