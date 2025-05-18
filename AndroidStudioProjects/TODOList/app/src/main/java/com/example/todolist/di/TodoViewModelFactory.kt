package com.example.todolist.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.remote.TodoApiService
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.presentation.detail.TodoDetailViewModel
import com.example.todolist.presentation.list.TodoListViewModel

// di/TodoViewModelFactory.kt
class TodoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
            val database = TodoDatabase.getDatabase(context)
            val repository = TodoRepository(
                TodoApiService.create(),
                database.todoDao()
            )
            return TodoListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class TodoDetailViewModelFactory(
    private val context: Context,
    private val todoId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoDetailViewModel::class.java)) {
            val database = TodoDatabase.getDatabase(context)
            val repository = TodoRepository(
                TodoApiService.create(),
                database.todoDao()
            )
            return TodoDetailViewModel(repository, todoId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}