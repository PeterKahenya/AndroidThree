package com.example.androidthree.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidthree.data.Todo
import com.example.androidthree.data.TodosRepository
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Todos View Model
@HiltViewModel
class TodosViewModel @Inject constructor(
    private val todosRepository: Lazy<TodosRepository>
) : ViewModel() {
    private val _state = MutableStateFlow(TodosViewState())
    val state = _state.asStateFlow()
    private val _createState = MutableStateFlow(TodosCreateState())
    val createState = _createState.asStateFlow()
    init {
        fetchTodos()
    }
    private fun fetchTodos() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            try {
                val todos = todosRepository.get().fetchTodos()
                _state.update {
                    it.copy(todos = todos, isLoading = false)
                }
                println(todos)
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    fun createTodo(todo: Todo) {
        viewModelScope.launch {
            _createState.update {
                it.copy(isLoading = true)
            }
            try{
                val newTodo = todosRepository.get().createTodo(todo)
                println("New Todo: $newTodo")
                _createState.update {
                    it.copy(isLoading = false)
                }
                _state.update {
                    it.copy(todos = it.todos + newTodo)
                }
            }catch (e: Exception) {
                _createState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }
}