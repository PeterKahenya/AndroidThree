package com.example.androidthree.ui

import com.example.androidthree.data.Todo

// Todos View State
data class TodosViewState(
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList(),
    val error: String? = null
)



// Todos Create State
data class TodosCreateState(
    val isLoading: Boolean = false,
    val error: String? = null
)