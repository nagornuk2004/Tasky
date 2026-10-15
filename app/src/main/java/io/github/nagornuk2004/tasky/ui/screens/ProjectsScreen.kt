package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import io.github.nagornuk2004.tasky.data.model.Project
import io.github.nagornuk2004.tasky.ui.components.ProjectItem

@Composable
fun ProjectsScreen(projects: List<Project>) {
        LazyColumn() {
            items(projects.size) { index -> ProjectItem(projects[index], {} ) }
        }
}