package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.nagornuk2004.tasky.data.model.Task
import java.util.UUID

class TasksViewModel : ViewModel() {
    var tasks by mutableStateOf(emptyList<Task>())
        private set

    var deletedTask: Pair<Task, Int>? = null

    fun createTask(task: Task) {
        tasks = tasks + task
    }

    fun deleteTask(uuid: UUID, showSnackbar: () -> Unit) {
        tasks = tasks.filter {
            if (it.id != uuid) true
            else {
                deletedTask = Pair(it, tasks.indexOf(it))
                false
            }
        }
        showSnackbar()
    }

    fun restoreDeletedTask() {
        val (task, index) = deletedTask!!
        tasks = tasks.toMutableList().apply {
            add(index, task)
        }
    }

    fun updateTask(task: Task) {
        tasks = tasks.map { if (it.id == task.id) task else it }
    }
}