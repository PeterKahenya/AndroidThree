package com.example.androidthree.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidthree.MainActivity
import com.example.androidthree.di.AppModule
import com.example.androidthree.ui.theme.AndroidThreeTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(AppModule::class)
class ComponentsTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            AndroidThreeTheme {
                val viewModel = hiltViewModel<TodosViewModel>()
                TodosScreen(viewModel)
            }
        }
    }

    @Test
    fun testTodosScreen() {
        composeTestRule.onNodeWithTag("todosCreateForm").assertExists()
        Thread.sleep(1000) // wait for todos to load
        composeTestRule.onNodeWithTag("todosList").assertExists()
    }

    @Test
    fun testAddTodo() {
        composeTestRule.onNodeWithTag("todosCreateForm").assertExists()
        composeTestRule.onNodeWithTag("todosCreateFormTitle").performTextInput("Test Todo")
        composeTestRule.onNodeWithTag("todosCreateFormButton").performClick()
        Thread.sleep(3000)
        composeTestRule.onNodeWithTag("todosCreateFormTitle").assertIsEnabled()
        composeTestRule.onNodeWithTag("todosList").assertExists()
        composeTestRule.onNodeWithText("Test Todo").assertExists()
    }
}