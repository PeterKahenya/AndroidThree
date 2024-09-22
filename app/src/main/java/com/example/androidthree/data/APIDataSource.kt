package com.example.androidthree.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Todos API
interface TodosAPIService{
    @GET("/todos")
    suspend fun getTodos(): List<Todo>

    @POST("/todos")
    suspend fun createTodo(@Body todo: Todo): Todo
}

// Network Module
object NetworkModule {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun createApiService(): TodosAPIService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodosAPIService::class.java)
    }
}