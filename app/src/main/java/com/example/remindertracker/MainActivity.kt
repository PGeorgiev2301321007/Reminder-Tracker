package com.example.remindertracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.remindertracker.data.db.AppDatabase
import com.example.remindertracker.data.repository.ReminderRepository
import com.example.remindertracker.presenter.AddReminderPresenter
import com.example.remindertracker.presenter.ReminderListPresenter
import com.example.remindertracker.ui.add.AddReminderScreen
import com.example.remindertracker.ui.list.ReminderListScreen
import com.example.remindertracker.ui.theme.ReminderTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ReminderTrackerTheme {

                // Database + repository
                val db = AppDatabase.getInstance(this)
                val repository = ReminderRepository(db.reminderDao())
                val listPresenter = ReminderListPresenter(repository)

                // Local UI state → това замества ViewModel.isCreating
                var showAddScreen by remember { mutableStateOf(false) }

                if (showAddScreen) {

                    // Presenter за екрана за добавяне
                    val presenter = AddReminderPresenter(
                        repository = repository,
                        onReminderSaved = {
                            showAddScreen = false
                        }
                    )

                    AddReminderScreen(
                        presenter = presenter,
                        onCancel = { showAddScreen = false }
                    )

                } else {

                    ReminderListScreen(
                        repository = repository,
                        onAddClicked = { showAddScreen = true }
                    )
                }
            }
        }
    }
}
