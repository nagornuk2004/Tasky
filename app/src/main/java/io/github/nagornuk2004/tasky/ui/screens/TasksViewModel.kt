package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.nagornuk2004.tasky.data.model.Task

class TasksViewModel : ViewModel() {
    var tasks by mutableStateOf(emptyList<Task>())
        private set

    fun createTask(task: Task) {
        tasks = tasks + task
    }
}