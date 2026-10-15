package io.github.nagornuk2004.tasky.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.github.nagornuk2004.tasky.R

@Composable
fun TasksScreen(navigateToForm: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(navigateToForm) {
                Icon(painterResource(R.drawable.ic_create_item), "Create Task")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                ListItem(
                    { Text("Wash dishes") }
                )
            }
            item {
                ListItem(
                    { Text("Clean room") }
                )
            }
            item {
                ListItem(
                    { Text("Complete Mobile App Dev Assignment") }
                )
            }
        }
    }
}