package io.github.nagornuk2004.tasky.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.github.nagornuk2004.tasky.data.model.Project
import java.util.UUID

class TaskyViewModel : ViewModel() {
    var projects by mutableStateOf(emptyList<Project>())
        private set

    fun createProject(project: Project) {
        projects = projects + project
    }

    fun renameProject(uuid: UUID, name: String) {
        projects = projects.map { project ->
            if (project.id == uuid) project.copy(name = name)
            else project
        }
    }

    fun deleteProject(uuid: UUID) {
        projects = (projects - projects.find { project ->
            project.id == uuid
        }) as List<Project>
    }
}