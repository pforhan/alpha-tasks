package alphainterplanetary.tasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val details: String?,
    val showDate: Long?,
    val repeatInfo: String?,
    val allowBackup: Boolean = true,
    val isCompleted: Boolean = false
)
