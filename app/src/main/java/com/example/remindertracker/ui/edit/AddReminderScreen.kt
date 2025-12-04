package com.example.remindertracker.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.remindertracker.presenter.AddReminderPresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderScreen(
    presenter: AddReminderPresenter,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Reminder") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    presenter.saveReminder(title, description)
                }
            ) {
                Text("Save")
            }

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCancel
            ) {
                Text("Cancel")
            }
        }
    }
}
