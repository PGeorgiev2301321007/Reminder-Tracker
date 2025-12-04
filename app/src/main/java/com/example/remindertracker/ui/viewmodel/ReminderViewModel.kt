package com.example.remindertracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remindertracker.data.model.Reminder
import com.example.remindertracker.data.repository.ReminderRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReminderViewModel(
    private val repository: ReminderRepository
) : ViewModel() {

    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders: StateFlow<List<Reminder>> = _reminders

    fun loadReminders() {
        viewModelScope.launch {
            _reminders.value = repository.getAll()
        }
    }

    fun addReminder(reminder: Reminder) {
        viewModelScope.launch {
            repository.insert(reminder)
            loadReminders()
        }
    }

    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch {
            repository.delete(reminder)
            loadReminders()
        }
    }
}
