package com.example.todolist.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController

// navigation/NavigationManager.kt
object NavigationManager {
    private val navController = mutableStateOf<NavController?>(null)

    fun setNavController(newNavController: NavController) {
        navController.value = newNavController
    }

    fun navigateTo(route: String) {
        navController.value?.navigate(route)
    }

    fun navigateBack() {
        navController.value?.popBackStack()
    }
}

// navigation/Routes.kt
object Routes {
    const val TODO_LIST = "todo_list"
    const val TODO_DETAIL = "todo_detail/{todoId}"

    fun todoDetail(todoId: Int): String {
        return "todo_detail/$todoId"
    }
}