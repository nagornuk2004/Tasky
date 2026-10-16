package io.github.nagornuk2004.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import io.github.nagornuk2004.tasky.ui.navigation.TaskyNavigator
import io.github.nagornuk2004.tasky.ui.screens.TasksViewModel
import io.github.nagornuk2004.tasky.ui.theme.TaskyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tasksViewModel = TasksViewModel()
        enableEdgeToEdge()
        setContent {
            TaskyTheme {
                TaskyApp(tasksViewModel)
            }
        }
    }
}

@Composable
fun TaskyApp(tasksViewModel: TasksViewModel) {
    TaskyNavigator(tasksViewModel)
}
