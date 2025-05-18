package com.example.todolist.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.data.local.entity.TodoEntity
import com.example.todolist.di.TodoDetailViewModelFactory
import com.example.todolist.navigation.NavigationManager
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

// presentation/detail/TodoDetailScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(todoId: Int) {
    val viewModel: TodoDetailViewModel = viewModel(
        factory = TodoDetailViewModelFactory(LocalContext.current, todoId)
    )
    val todo by viewModel.todo.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TODO Details") },
                navigationIcon = {
                    IconButton(onClick = { NavigationManager.navigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (todo == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            TodoDetailContent(todo = todo!!, modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun TodoDetailContent(todo: TodoEntity, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = todo.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Divider()

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = null
            )
            Text(
                text = if (todo.completed) "Completed" else "Not completed",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Text(
            text = "User ID: ${todo.userId}",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "TODO ID: ${todo.id}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}