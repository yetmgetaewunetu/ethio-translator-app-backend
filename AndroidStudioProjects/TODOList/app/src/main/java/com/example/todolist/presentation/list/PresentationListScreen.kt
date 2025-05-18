package com.example.todolist.presentation.list


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.data.local.entity.TodoEntity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


// presentation/list/TodoListScreen.kt
@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = viewModel(factory = com.example.todolist.di.TodoViewModelFactory (LocalContext.current)),
    onTodoClick: (Int) -> Unit
) {
    val todos by viewModel.todos.collectAsState(initial = emptyList())
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
       isLoading =  viewModel.isRefreshing.value
    }

    LaunchedEffect(Unit) {
        errorMessage =  viewModel.errorMessage.value
    }
    Text(text = "Todo Items", modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
        .background(Color.DarkGray),
        textAlign = TextAlign.Center,
        fontSize = 27.sp,
        fontWeight = FontWeight.Bold
    )
    Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
        if (isLoading && todos.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            if (errorMessage != null) {
                ErrorMessage(errorMessage = errorMessage.toString(), onRetry = { viewModel.loadTodos() })
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(todos) { todo ->
                    TodoItem(todo = todo, onItemClick = { onTodoClick(todo.id) })
                }
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadTodos()
    }
}

@Composable
fun TodoItem(todo: TodoEntity, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ErrorMessage(errorMessage: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}