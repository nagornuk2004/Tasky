package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.github.nagornuk2004.tasky.R
import io.github.nagornuk2004.tasky.data.model.Task
import io.github.nagornuk2004.tasky.ui.components.TaskListItem

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel, navigateToForm: (Task?) -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton({ navigateToForm(null) }) {
                Icon(painterResource(R.drawable.ic_create_item), "Create Task")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(count = tasksViewModel.tasks.size) {
                val task = tasksViewModel.tasks[it]
                TaskListItem(
                    task = task,
                    markTaskAsDone = { tasksViewModel.deleteTask(task.id) },
                    modifyTask = { navigateToForm(task) }
                )
            }
        }
    }
}