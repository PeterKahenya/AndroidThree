package com.example.androidthree.data

import android.app.Application
import com.example.androidthree.R

// Todos Repository
interface TodosRepository {
    suspend fun fetchTodos(): List<Todo>
    suspend fun createTodo(todo: Todo): Todo
}
class TodosRepositoryImpl(
    private val todosApi: TodosAPIService,
    private val app: Application
) : TodosRepository {

    init {
        val appName = app.getString(R.string.app_name)
        println("Hello from $appName")
    }
    override suspend fun fetchTodos(): List<Todo> {
        return todosApi.getTodos()
    }

    override suspend fun createTodo(todo: Todo): Todo {
        return todosApi.createTodo(todo)
    }

}