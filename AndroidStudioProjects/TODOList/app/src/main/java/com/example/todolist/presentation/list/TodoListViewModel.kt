package com.example.todolist.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.repository.TodoRepository
import kotlinx.coroutines.launch

// presentation/list/TodoListViewModel.kt
class TodoListViewModel(
    private val repository: TodoRepository
) : ViewModel() {
    val todos = repository.todos

    private val _isRefreshing: MutableState<Boolean> = mutableStateOf(false)
    val isRefreshing = _isRefreshing

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage = _errorMessage

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _errorMessage.value = null
            try {
                repository.refreshTodos()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load todos: ${e.message}"
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}