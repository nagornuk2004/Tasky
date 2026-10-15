package io.github.nagornuk2004.tasky.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.nagornuk2004.tasky.data.model.Project

@Composable
fun ProjectItem(project: Project, onClick: () -> Unit) {
    ListItem(
        headlineContent = {
            Text(project.name)
        },
        modifier = Modifier.clickable {
            onClick()
        }
    )
}