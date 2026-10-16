package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.github.nagornuk2004.tasky.R
import io.github.nagornuk2004.tasky.data.model.Task
import io.github.nagornuk2004.tasky.ui.components.TaskListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(tasksViewModel: TasksViewModel, navigateToForm: (Task?) -> Unit) {
    val searchFieldState = rememberTextFieldState("")
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TextField(
                        state = searchFieldState,
                        label = { Text("Search") }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton({ navigateToForm(null) }) {
                Icon(painterResource(R.drawable.ic_create_item), "Create Task")
            }
        }
    ) { innerPadding ->
        val filteredTasks = tasksViewModel.tasks.filter {
            it.name.contains(searchFieldState.text, ignoreCase = true) ||
                    it.description.contains(searchFieldState.text, ignoreCase = true)
        }
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(count = filteredTasks.size) {
                val task = filteredTasks[it]
                TaskListItem(
                    task = task,
                    markTaskAsDone = { tasksViewModel.deleteTask(task.id) },
                    modifyTask = { navigateToForm(task) }
                )
            }
        }
    }
}