package alphainterplanetary.tasks.ui.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import alphainterplanetary.tasks.data.Task
import alphainterplanetary.tasks.data.TasksRepository
import alphainterplanetary.tasks.notifications.NotificationScheduler
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AddEditTaskViewModel(
    private val tasksRepository: TasksRepository,
    private val savedStateHandle: SavedStateHandle,
    private val notificationScheduler: NotificationScheduler
) : ViewModel() {

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    private val taskId: Int = checkNotNull(savedStateHandle["taskId"])

    init {
        if (taskId != -1) {
            viewModelScope.launch {
                taskUiState = tasksRepository.getTaskStream(taskId)
                    .filterNotNull()
                    .first()
                    .toTaskUiState()
            }
        }
    }

    fun updateUiState(newTaskUiState: TaskUiState) {
        taskUiState = newTaskUiState
    }

    suspend fun saveTask() {
        if (taskId == -1) {
            val newTask = taskUiState.toTask()
            tasksRepository.insertTask(newTask)
            notificationScheduler.scheduleNotification(newTask)
        } else {
            val updatedTask = taskUiState.toTask()
            tasksRepository.updateTask(updatedTask)
            notificationScheduler.scheduleNotification(updatedTask)
        }
    }

    suspend fun deleteTask() {
        val taskToDelete = taskUiState.toTask()
        tasksRepository.deleteTask(taskToDelete)
        notificationScheduler.cancelNotification(taskToDelete.id)
    }
}

data class TaskUiState(
    val id: Int = 0,
    val title: String = "",
    val details: String = "",
)

fun TaskUiState.toTask(): Task = Task(
    id = id,
    title = title,
    details = details,
    showDate = null,
    repeatInfo = null
)

fun Task.toTaskUiState(): TaskUiState = TaskUiState(
    id = id,
    title = title,
    details = details ?: ""
)
