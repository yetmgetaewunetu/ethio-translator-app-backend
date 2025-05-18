package com.example.todolist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


// data/local/entity/TodoEntity.kt
@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)