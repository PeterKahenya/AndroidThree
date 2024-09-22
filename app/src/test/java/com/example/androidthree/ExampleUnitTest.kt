package com.example.androidthree

import com.example.androidthree.data.Todo
import com.example.androidthree.data.TodosRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

// create fake TodosRepository
class FakeTodosRepository : TodosRepository {
    private var fakeTodos = listOf(
        Todo(userId = 1, title = "a", completed = false),
        Todo(userId = 1, title = "b", completed = true)
    )
    override suspend fun fetchTodos(): List<Todo> {
        return fakeTodos
    }

    override suspend fun createTodo(todo: Todo): Todo {
        todo.id = fakeTodos.size + 1
        fakeTodos = fakeTodos + todo
        return todo
    }

}


class ExampleUnitTest {

    @Before
    fun setUp() {
        println("Setting up test")
    }
    @Test
    fun `addition correct`() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun `subtraction correct`() {
        assertEquals(48, 50 - 2)
    }
}