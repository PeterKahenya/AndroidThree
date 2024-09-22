package com.example.androidthree.data

// Todos Model
data class Todo(
    val userId: Int,
    var id: Int? = null,
    var title: String,
    val completed: Boolean
)