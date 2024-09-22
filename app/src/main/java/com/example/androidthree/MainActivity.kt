package com.example.androidthree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidthree.data.NetworkModule
import com.example.androidthree.data.TodosRepositoryImpl
import com.example.androidthree.ui.TodosScreen
import com.example.androidthree.ui.TodosViewModel
import com.example.androidthree.ui.theme.AndroidThreeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Starting MainActivity")
        setContent {
            AndroidThreeTheme {
                val viewModel = hiltViewModel<TodosViewModel>()
                TodosScreen(viewModel)
            }
        }

    }
}


