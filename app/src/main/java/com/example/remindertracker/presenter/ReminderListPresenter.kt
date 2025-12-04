package com.example.remindertracker.presenter

import com.example.remindertracker.data.repository.ReminderRepository
import com.example.remindertracker.data.model.Reminder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderListPresenter(
    private val repository: ReminderRepository,
) {
    private val scope = CoroutineScope(Dispatchers.IO)

    fun deleteReminder(reminder: Reminder, onDeleted: () -> Unit) {
        scope.launch {
            repository.delete(reminder)
            onDeleted()
        }
    }
}
