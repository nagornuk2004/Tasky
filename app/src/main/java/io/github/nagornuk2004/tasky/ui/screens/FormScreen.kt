package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.nagornuk2004.tasky.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(navigateToTasks: () -> Unit) {
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
                    IconButton(onClick = {}) { // TODO: Save Task
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
            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
        ) {
            item {
                TextField(
                    modifier = Modifier.padding(10.dp, 10.dp),
                    state = rememberTextFieldState(initialText = ""),
                    label = { Text("Name") },
                    lineLimits = TextFieldLineLimits.SingleLine
                )
            }
        }
    }
}