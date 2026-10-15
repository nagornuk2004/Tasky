package io.github.nagornuk2004.tasky.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.nagornuk2004.tasky.data.model.Task
import java.time.format.DateTimeFormatter

@Composable
fun TaskListItem(task: Task) {
    ListItem(
        headlineContent = { Text(task.name) },
        overlineContent = { Row(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(1f)) {
                Text(task.tag.orEmpty())
            }
            if (task.priority != null) Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
                Text(task.priority.toString())
            }
        } },
        supportingContent = { Row(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(1f)) {
                Text(task.description.orEmpty())
            }
            if (task.due != null) Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
                Text("Due: " + task.due!!.format(DateTimeFormatter.ofPattern("d MMM")))
            }
        } }
    )
}