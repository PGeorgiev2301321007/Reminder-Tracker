package com.example.remindertracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.remindertracker.data.db.AppDatabase
import com.example.remindertracker.data.repository.ReminderRepository
import com.example.remindertracker.presenter.ReminderViewModelFactory
import com.example.remindertracker.ui.theme.ReminderTrackerTheme
import com.example.remindertracker.ui.viewmodel.ReminderViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.getInstance(this)
        val repository = ReminderRepository(db.reminderDao())
        val viewModel: ReminderViewModel by viewModels {
            ReminderViewModelFactory(repository)
        }

        setContent {
            ReminderTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        viewModel.loadReminders()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello Pesho!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReminderTrackerTheme {
        Greeting("Android")
    }
}