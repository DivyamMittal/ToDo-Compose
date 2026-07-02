package com.example.todoapp_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp_compose.ui.screens.ToDoHomeScreen
import com.example.todoapp_compose.ui.theme.ToDoAppComposeTheme
import com.example.todoapp_compose.viewModel.TodoViewModel
import com.example.todoapp_compose.viewModel.TodoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val todoViewModel : TodoViewModel by viewModels {
            TodoViewModelFactory(application)
        }
        setContent {
            ToDoAppComposeTheme {
                ToDoHomeScreen(viewModel = todoViewModel)
            }
        }
    }
}

