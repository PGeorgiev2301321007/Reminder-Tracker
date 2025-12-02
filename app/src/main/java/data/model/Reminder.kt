package data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

class Reminder {
    @Entity(tableName = "reminders")
    data class Reminder(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val title: String,
        val description: String?,
        val timestamp: Long
    )

}