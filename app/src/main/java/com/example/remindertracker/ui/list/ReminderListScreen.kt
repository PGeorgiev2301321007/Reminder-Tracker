package com.example.remindertracker.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.remindertracker.data.model.Reminder
import com.example.remindertracker.data.repository.ReminderRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderListScreen(
    repository: ReminderRepository,
    onAddClicked: () -> Unit
) {
    var reminders by remember { mutableStateOf(emptyList<Reminder>()) }

    // Зареждаме данните от базата при старт
    LaunchedEffect(Unit) {
        reminders = repository.getAll()
    }

    // Функция за изтриване
    suspend fun deleteReminder(reminder: Reminder) {
        repository.delete(reminder)
        reminders = repository.getAll()   // ОБНОВЯВА UI
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reminders") },
                actions = {
                    IconButton(onClick = onAddClicked) {
                        Text("+")
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {
            items(reminders) { reminder ->
                ReminderRow(
                    reminder = reminder,
                    onDelete = {
                        scope.launch {
                            deleteReminder(reminder)
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun ReminderRow(
    reminder: Reminder,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = reminder.title,
                style = MaterialTheme.typography.titleMedium
            )
            if (!reminder.description.isNullOrEmpty()) {
                Text(
                    text = reminder.description!!,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete reminder",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }

    Divider()
}
