package io.github.nagornuk2004.tasky.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.nagornuk2004.tasky.ui.TaskyViewModel
import io.github.nagornuk2004.tasky.ui.screens.AppDestinations
import io.github.nagornuk2004.tasky.ui.screens.FormScreen
import io.github.nagornuk2004.tasky.ui.screens.OverviewScreen
import kotlinx.serialization.Serializable

@Serializable
object Overview
@Serializable
data class Form(val destination: AppDestinations)

@Composable
fun TaskyNavController(taskyViewModel: TaskyViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Overview,
        enterTransition = {
            slideInHorizontally(animationSpec = tween(700))
        },
        exitTransition = {
            slideOutHorizontally(animationSpec = tween(700))
        },
    ) {
        composable<Overview> { OverviewScreen(taskyViewModel) {
            navController.navigate(route = Form)
        } }
        composable<Form>{ FormScreen(taskyViewModel) {
            navController.navigate(route = Overview)
        } }
    }
}