package com.example.androidthree.di

import android.app.Application
import com.example.androidthree.data.NetworkModule
import com.example.androidthree.data.TodosAPIService
import com.example.androidthree.data.TodosRepository
import com.example.androidthree.data.TodosRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule{

    @Provides
    @Singleton
    fun provideAPIService(): TodosAPIService {
        return NetworkModule.createApiService()
    }

    @Provides
    @Singleton
    fun provideTodosRepository(apiService: TodosAPIService, app: Application): TodosRepository {
        return TodosRepositoryImpl(apiService,app)
    }
}