package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.github.nagornuk2004.tasky.R
import io.github.nagornuk2004.tasky.ui.TaskyViewModel

@Composable
fun OverviewScreen(taskyViewModel: TaskyViewModel, onNavigateToForm: (currentDestination: AppDestinations) -> Unit) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.TASKS) }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = {
                        currentDestination = it
                    }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(onClick = onNavigateToForm(currentDestination)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_create_item),
                        contentDescription = "Create Item"
                    )
                }
            }
        ) { innerPadding -> (
                Column(modifier = Modifier.padding(innerPadding)) {
                    when (currentDestination) {
                        AppDestinations.TASKS -> Text("TODO TASKS")
                        AppDestinations.PROJECTS -> ProjectsScreen(taskyViewModel.projects)
                    }
                })
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    TASKS("Tasks", R.drawable.ic_tasks),
    PROJECTS("Projects", R.drawable.ic_projects),
}