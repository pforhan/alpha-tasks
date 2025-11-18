package alphainterplanetary.tasks.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import alphainterplanetary.tasks.TasksApplication
import alphainterplanetary.tasks.ui.add_edit_task.AddEditTaskViewModel
import alphainterplanetary.tasks.ui.home.HomeViewModel
import alphainterplanetary.tasks.ui.settings.SettingsViewModel
import alphainterplanetary.tasks.ui.share_task.ShareTaskViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(tasksApplication().container.tasksRepository)
        }

        initializer {
            AddEditTaskViewModel(
                tasksRepository = tasksApplication().container.tasksRepository,
                savedStateHandle = this.createSavedStateHandle(),
                notificationScheduler = tasksApplication().notificationScheduler
            )
        }

        initializer {
            SettingsViewModel(tasksApplication().container.tasksRepository)
        }

        initializer {
            ShareTaskViewModel(
                tasksRepository = tasksApplication().container.tasksRepository,
                savedStateHandle = this.createSavedStateHandle()
            )
        }
    }
}

fun CreationExtras.tasksApplication(): TasksApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TasksApplication)
