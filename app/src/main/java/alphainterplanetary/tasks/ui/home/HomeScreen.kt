package alphainterplanetary.tasks.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import alphainterplanetary.tasks.data.Task
import alphainterplanetary.tasks.ui.AppViewModelProvider

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onAddTask: () -> Unit,
    onTaskClick: (Task) -> Unit,
    onSettingsClick: () -> Unit
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = onAddTask) {
            Text("Add Task")
        }
        Button(onClick = onSettingsClick) {
            Text("Settings")
        }
        TaskList(
            taskList = homeUiState.taskList,
            onTaskCompletedChange = { task, completed ->
                viewModel.updateTask(task.copy(isCompleted = completed))
            },
            onTaskClick = onTaskClick,
            onDismiss = { task ->
                viewModel.updateTask(task.copy(showDate = System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            }
        )
    }
}

@Composable
fun TaskList(
    taskList: List<Task>,
    onTaskCompletedChange: (Task, Boolean) -> Unit,
    onTaskClick: (Task) -> Unit,
    onDismiss: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = taskList, key = { task -> task.id }) { task ->
            TaskItem(
                task = task,
                onCompletedChange = { onTaskCompletedChange(task, it) },
                onTaskClick = { onTaskClick(task) },
                onDismiss = { onDismiss(task) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    task: Task,
    onCompletedChange: (Boolean) -> Unit,
    onTaskClick: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToEnd) {
                onDismiss()
                true
            } else {
                false
            }
        }
    )
    SwipeToDismiss(
        state = dismissState,
        background = { },
        dismissContent = {
            Row(modifier = modifier.clickable(onClick = onTaskClick)) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onCompletedChange
                )
                Text(text = task.title)
            }
        }
    )
}
