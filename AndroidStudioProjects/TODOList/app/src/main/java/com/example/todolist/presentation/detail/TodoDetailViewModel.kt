package com.example.todolist.presentation.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.remote.TodoApiService
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.presentation.list.TodoListViewModel

// presentation/detail/TodoDetailViewModel.kt
class TodoDetailViewModel(
    private val repository: TodoRepository,
    private val todoId: Int
) : ViewModel() {
    val todo = repository.getTodoById(todoId)
}