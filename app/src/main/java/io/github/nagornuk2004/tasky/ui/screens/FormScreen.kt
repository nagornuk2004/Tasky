package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
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
import androidx.compose.material3.TextButton
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
import java.time.format.DateTimeFormatter

private const val DATE_PATTERN = "d MMM"
private val PAD = 10.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(tasksViewModel: TasksViewModel, selectedTask: Task?, navigateToTasks: () -> Unit) {
    val nameState = rememberTextFieldState(selectedTask?.name.orEmpty())
    val descriptionState = rememberTextFieldState(selectedTask?.description.orEmpty())
    val tagState = rememberTextFieldState(selectedTask?.tag.orEmpty())
    val dueState = rememberDatePickerState(selectedTask?.due)
    val priorityState = rememberTextFieldState(selectedTask?.priority?.label ?: "Medium")
    val dateFieldState = rememberTextFieldState(
        selectedTask?.due
            ?.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) ?: ""
    )
    var showDatePicker by remember { mutableStateOf(false) }
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    dateFieldState.setTextAndPlaceCursorAtEnd(
                        dueState.getSelectedDate()
                            ?.format(DateTimeFormatter.ofPattern(DATE_PATTERN))
                            ?: ""
                    )
                    showDatePicker = false
                }) {
                    Text("Confirm")
                }
            }
        ) {
            DatePicker(state = dueState)
        }
    }
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
                            val task = Task(
                                name = nameState.text.toString(),
                                description = descriptionState.text.toString(),
                                tag = tagState.text.toString(),
                                due = dueState.getSelectedDate(),
                                priority = Priority.fromString(priorityState.text.toString())
                            )
                            if (selectedTask == null) tasksViewModel.createTask(task)
                            else tasksViewModel.updateTask(task.copy(id = selectedTask.id))
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        state = nameState,
                        modifier = Modifier
                            .padding(PAD, PAD)
                            .fillMaxWidth(),
                        label = { Text("Name") },
                        lineLimits = TextFieldLineLimits.SingleLine
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        state = tagState,
                        modifier = Modifier.padding(PAD, PAD),
                        label = { Text("Tag") },
                        lineLimits = TextFieldLineLimits.SingleLine
                    )
                }
            }
            TextField(
                state = descriptionState,
                modifier = Modifier
                    .padding(PAD, PAD)
                    .fillMaxWidth(),
                label = { Text("Description") }
            )
            Row(modifier = Modifier.fillMaxWidth()) {
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
                Column(modifier = Modifier.weight(1f)) {
                    Box {
                        OutlinedTextField(
                            state = dateFieldState,
                            readOnly = true,
                            enabled = true,
                            label = { Text("Due Date") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PAD)
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .padding(top = PAD)
                                .padding(PAD)
                                .clickable { showDatePicker = true }
                        )
                    }
                }
            }
        }
    }
}