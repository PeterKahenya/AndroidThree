package com.example.androidthree.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidthree.data.Todo

@Composable
fun TodosScreen(
    viewModel: TodosViewModel
){

    val state by viewModel.state.collectAsStateWithLifecycle()
    Column {
        TodosCreateForm(viewModel)
        TodosContent(state)
    }
}

@Composable
fun TodosContent(state: TodosViewState){
    if(state.isLoading){
        TodoLoading()
    } else if(state.todos.isNotEmpty()){
        println("Todos: ${state.todos}")
        TodosList(state.todos)
    } else if(state.error != null){
        TodosError(message = state.error)
    }
}


// Create Todos Form
@Composable
fun TodosCreateForm(
    viewModel: TodosViewModel
){
    val createState by viewModel.createState.collectAsStateWithLifecycle()
    val todoState: MutableState<Todo> = remember { mutableStateOf(Todo(userId = 1, title = "", completed = false)) }
    Column(
        modifier = Modifier
            .testTag("todosCreateForm")
    ) {
        Text(text = "Create Todo")
        Row {
            TextField(
                value = todoState.value.title,
                onValueChange = {
                    todoState.value = todoState.value.copy(title = it)
                },
                enabled = !createState.isLoading,
                label = { Text(text = "Title") },
                modifier = Modifier.testTag("todosCreateFormTitle")
            )
            Button(
                onClick = {
                    println("Create Todo: ${todoState.value}")
                    viewModel.createTodo(todoState.value)
                },
                modifier = Modifier.testTag("todosCreateFormButton"),
            ) {
                Text(text = "Create")
            }
        }
        if(createState.error != null){
            Text(text = "Error: ${createState.error}")
        }
    }

}


@Composable
fun TodoLoading(){
    Text(text = "Loading")
}


@Composable
fun TodosError(message: String?){
    if (message != null) {
        Text(text = message)
    }
}

@Composable
fun TodosList(todos: List<Todo>){
    LazyColumn(
        modifier = Modifier
            .testTag("todosList")
    ) {
        items(todos){ todo ->
            TodoItem(todo = todo)
        }
    }
}

@Composable
fun TodoItem(todo: Todo){
    Text(text = todo.title)
}