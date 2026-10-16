package io.github.nagornuk2004.tasky.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.nagornuk2004.tasky.data.model.Task
import io.github.nagornuk2004.tasky.ui.screens.FormScreen
import io.github.nagornuk2004.tasky.ui.screens.TasksScreen
import io.github.nagornuk2004.tasky.ui.screens.TasksViewModel
import kotlinx.serialization.Serializable

@Serializable
object Tasks

@Serializable
object Form

@Composable
fun TaskyNavigator(tasksViewModel: TasksViewModel) {
    val navController = rememberNavController()
    var selectedTask: Task? = null;
    NavHost(
        navController = navController,
        startDestination = Tasks,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        predictivePopEnterTransition = { EnterTransition.None },
        predictivePopExitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300),
                towards = AnimatedContentTransitionScope.SlideDirection.Right
            )
        }
    ) {
        composable<Tasks> {
            TasksScreen(tasksViewModel) {
                selectedTask = it
                navController.navigate(Form)
            }
        }
        composable<Form>(
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(300),
                    towards = AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    animationSpec = tween(300),
                    towards = AnimatedContentTransitionScope.SlideDirection.Right
                )
            }
        ) {
            FormScreen(tasksViewModel, selectedTask) {
                navController.popBackStack()
            }
        }
    }
}