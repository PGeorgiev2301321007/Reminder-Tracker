package com.example.remindertracker.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.remindertracker.data.model.Reminder
import com.example.remindertracker.data.repository.ReminderRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderListScreen(
    repository: ReminderRepository,
    onAddClicked: () -> Unit
) {
    var reminders by remember { mutableStateOf(emptyList<Reminder>()) }

    // Зареждаме данните от базата
    LaunchedEffect(Unit) {
        reminders = repository.getAll()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reminders") },
                actions = {
                    IconButton(onClick = onAddClicked) {
                        Text("+") // Можеш да сложиш икона
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            items(reminders) { reminder ->
                ReminderItem(reminder)
            }
        }
    }
}

@Composable
fun ReminderItem(reminder: Reminder) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = reminder.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        reminder.description?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
    }
}
