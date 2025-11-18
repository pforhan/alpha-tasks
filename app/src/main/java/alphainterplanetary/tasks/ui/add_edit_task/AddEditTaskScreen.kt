package alphainterplanetary.tasks.ui.add_edit_task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import alphainterplanetary.tasks.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEditTaskViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onSave: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = viewModel.taskUiState.title,
            onValueChange = { viewModel.updateUiState(viewModel.taskUiState.copy(title = it)) },
            label = { Text("Title") }
        )
        OutlinedTextField(
            value = viewModel.taskUiState.details,
            onValueChange = { viewModel.updateUiState(viewModel.taskUiState.copy(details = it)) },
            label = { Text("Details") }
        )
        Button(onClick = {
            coroutineScope.launch {
                viewModel.saveTask()
                onSave()
            }
        }) {
            Text("Save")
        }
        Button(onClick = {
            coroutineScope.launch {
                viewModel.deleteTask()
                onDelete()
            }
        }) {
            Text("Delete")
        }
        Button(onClick = onShare) {
            Text("Share")
        }
    }
}
