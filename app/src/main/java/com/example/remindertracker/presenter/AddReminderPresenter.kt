package com.example.remindertracker.presenter

import com.example.remindertracker.data.repository.ReminderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddReminderPresenter(
    private val repository: ReminderRepository,
    private val onReminderSaved: () -> Unit
) {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun saveReminder(title: String, description: String) {
        scope.launch {
            repository.insertReminder(title, description)
            onReminderSaved()
        }
    }
}