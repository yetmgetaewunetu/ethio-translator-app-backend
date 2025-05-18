package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.navigation.NavigationManager
import com.example.todolist.navigation.Routes
import com.example.todolist.presentation.detail.TodoDetailScreen
import com.example.todolist.presentation.list.TodoListScreen
import com.example.todolist.ui.theme.TodoAppTheme

// MainActivity.kt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoApp()
                }
            }
        }
    }
}

@Composable
fun TodoApp() {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        NavigationManager.setNavController(navController)
    }

    NavHost(
        navController = navController,
        startDestination = Routes.TODO_LIST
    ) {
        composable(Routes.TODO_LIST) {
            TodoListScreen(onTodoClick = { todoId ->
                navController.navigate(Routes.todoDetail(todoId))
            })
        }
        composable(Routes.TODO_DETAIL) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: 0
            TodoDetailScreen(todoId = todoId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppTheme {
        TodoListScreen(onTodoClick = {})
    }
}