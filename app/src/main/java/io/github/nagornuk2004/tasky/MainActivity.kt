package io.github.nagornuk2004.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.nagornuk2004.tasky.ui.navigation.TaskyNavigator
import io.github.nagornuk2004.tasky.ui.theme.TaskyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskyTheme {
                TaskyApp()
            }
        }
    }
}

@Preview
@Composable
fun TaskyApp() {
    TaskyNavigator()
}
