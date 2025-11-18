package alphainterplanetary.tasks.ui.share_task

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import alphainterplanetary.tasks.ui.AppViewModelProvider
import alphainterplanetary.tasks.utils.generateQrCodeBitmap

@Composable
fun ShareTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: ShareTaskViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val task by viewModel.task.collectAsState()
    val qrCodeBitmap by remember(task) {
        mutableStateOf(task?.let {
            val content = "${it.title}|${it.details}"
            generateQrCodeBitmap(content, 500)
        })
    }

    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Share Task: ${task?.title}")
        qrCodeBitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "QR Code for task",
                modifier = Modifier.size(250.dp)
            )
        }
    }
}
