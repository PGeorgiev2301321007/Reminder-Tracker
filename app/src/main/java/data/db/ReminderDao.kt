package data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import data.model.Reminder

class ReminderDao {
    @Dao
    interface ReminderDao {

        @Insert
        suspend fun insert(reminder: Reminder)

        @Update
        suspend fun update(reminder: Reminder)

        @Delete
        suspend fun delete(reminder: Reminder)

        @Query("SELECT * FROM reminders ORDER BY timestamp ASC")
        suspend fun getAll(): List<Reminder>
    }

}