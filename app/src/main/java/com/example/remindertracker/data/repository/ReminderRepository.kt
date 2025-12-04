package com.example.remindertracker.data.repository

import com.example.remindertracker.data.db.ReminderDao
import com.example.remindertracker.data.model.Reminder

class ReminderRepository(private val dao: ReminderDao) {

    suspend fun insertReminder(title: String, description: String) {
        val reminder = Reminder(
            id = 0,
            title = title,
            description = description,
            timestamp = System.currentTimeMillis()
        )
        dao.insert(reminder)
    }
    suspend fun insert(reminder: Reminder) {
        dao.insert(reminder)
    }

    suspend fun update(reminder: Reminder) {
        dao.update(reminder)
    }

    suspend fun delete(reminder: Reminder) {
        dao.delete(reminder)
    }

    suspend fun getAll(): List<Reminder> {
        return dao.getAll()
    }
}
