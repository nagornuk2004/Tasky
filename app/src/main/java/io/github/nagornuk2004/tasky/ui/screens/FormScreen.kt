package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.github.nagornuk2004.tasky.R
import io.github.nagornuk2004.tasky.ui.TaskyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(taskyViewModel: TaskyViewModel, onNavigateToOverview: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Item Form") },
                navigationIcon = { IconButton(
                    onClick = onNavigateToOverview
                ) {
                    Icon(
                        painterResource(R.drawable.ic_back),
                        contentDescription = "Go Back"
                    )
                }},
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            val currentDestination = AppDestinations.PROJECTS
            when (currentDestination) {
                AppDestinations.PROJECTS -> ProjectFormFields(taskyViewModel)
                AppDestinations.TASKS -> TaskFormFields(taskyViewModel)
            }
        }
    }
}
