package com.example.remindertracker.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remindertracker.data.repository.ReminderRepository
import com.example.remindertracker.ui.viewmodel.ReminderViewModel

class ReminderViewModelFactory(
    private val repository: ReminderRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            return ReminderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
