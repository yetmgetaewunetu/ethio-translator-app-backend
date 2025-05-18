package com.example.todolist.data.repository

import com.example.todolist.data.local.dao.TodoDao
import com.example.todolist.data.local.entity.TodoEntity
import com.example.todolist.data.remote.TodoApiService
import kotlinx.coroutines.flow.Flow

// data/repository/TodoRepository.kt
class TodoRepository(
    private val todoApiService: TodoApiService,
    private val todoDao: TodoDao
) {
    val todos: Flow<List<TodoEntity>> = todoDao.getAllTodos()

    suspend fun refreshTodos() {
        try {
            val remoteTodos = todoApiService.getTodos()
            todoDao.insertAll(remoteTodos.map { todo ->
                TodoEntity(
                    id = todo.id,
                    userId = todo.userId,
                    title = todo.title,
                    completed = todo.completed
                )
            })
        } catch (e: Exception) {
            // Handle error (network might be unavailable)
            // We'll show cached data if available
        }
    }

    fun getTodoById(id: Int): Flow<TodoEntity?> {
        return todoDao.getTodoById(id)
    }
}