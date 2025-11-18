package alphainterplanetary.tasks.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import alphainterplanetary.tasks.data.Task

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            val taskId = intent.getIntExtra("taskId", -1)
            val taskTitle = intent.getStringExtra("taskTitle")
            val taskDetails = intent.getStringExtra("taskDetails")

            if (taskId != -1 && taskTitle != null) {
                val notificationService = NotificationService(context)
                val task = Task(id = taskId, title = taskTitle, details = taskDetails, showDate = null, repeatInfo = null)
                notificationService.showNotification(task)
            }
        }
    }
}
