package com.example.colorfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.colorfinder.data.local.AppDatabase
import com.example.colorfinder.data.repository.ColorRepository
import com.example.colorfinder.ui.theme.ColorFinderTheme
import com.example.colorfinder.view.ColorListScreen
import com.example.colorfinder.viewmodel.MainViewModel
import com.example.colorfinder.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Initialize database, repository, and ViewModel
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = ColorRepository(database.colorDao())
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setContent {
            ColorFinderTheme {
                ColorListScreen(viewModel)
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorFinderTheme {
        Greeting("Android")
    }
}