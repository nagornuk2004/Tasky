package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.getSelectedDate
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.nagornuk2004.tasky.R
import io.github.nagornuk2004.tasky.data.model.Priority
import io.github.nagornuk2004.tasky.data.model.Task

val PAD = 10.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(tasksViewModel: TasksViewModel, navigateToTasks: () -> Unit) {
    val nameState = rememberTextFieldState("")
    val descriptionState = rememberTextFieldState("")
    val tagState = rememberTextFieldState("")
    val dueState = rememberDatePickerState()
    val priorityState = rememberTextFieldState(Priority.MEDIUM.label)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Task Form") },
                navigationIcon = {
                    IconButton(onClick = navigateToTasks) {
                        Icon(painterResource(R.drawable.ic_back), "Go Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (nameState.text.isNotBlank()) {
                            tasksViewModel.createTask(
                                Task(
                                    name = nameState.text as String,
                                    description = descriptionState.text.toString(),
                                    tag = tagState.text.toString(),
                                    due = dueState.getSelectedDate(),
                                    priority = Priority.fromString(priorityState.text.toString())
                                )
                            )
                            navigateToTasks()
                        }
                    }) {
                        Icon(
                            painterResource(R.drawable.ic_save),
                            contentDescription = "Save Item"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
        ) {
            item {
                TextField(
                    state = nameState,
                    modifier = Modifier
                        .padding(PAD, PAD)
                        .fillMaxWidth(),
                    label = { Text("Name") },
                    lineLimits = TextFieldLineLimits.SingleLine
                )
            }
            item {
                TextField(
                    state = descriptionState,
                    modifier = Modifier
                        .padding(PAD, PAD)
                        .fillMaxWidth(),
                    label = { Text("Description") }
                )
            }
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        TextField(
                            state = tagState,
                            modifier = Modifier.padding(PAD, PAD),
                            label = { Text("Tag") },
                            lineLimits = TextFieldLineLimits.SingleLine
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        var expanded by remember { mutableStateOf(false) }
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = it }) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                                    .padding(PAD, PAD),
                                readOnly = true,
                                state = priorityState,
                                label = { Text("Priority") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                Priority.entries.forEach { entry ->
                                    DropdownMenuItem(
                                        onClick = {
                                            priorityState.setTextAndPlaceCursorAtEnd(entry.label)
                                            expanded = false
                                        },
                                        text = { Text(entry.label) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            item {
                DatePicker(
                    state = dueState,
                    modifier = Modifier.padding(PAD, PAD)
                )
            }
        }
    }
}