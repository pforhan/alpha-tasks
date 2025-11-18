package alphainterplanetary.tasks

import android.app.Application
import alphainterplanetary.tasks.data.AppContainer
import alphainterplanetary.tasks.data.AppDataContainer
import alphainterplanetary.tasks.notifications.NotificationScheduler

class TasksApplication : Application() {
    lateinit var container: AppContainer
    lateinit var notificationScheduler: NotificationScheduler

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        notificationScheduler = NotificationScheduler(this)
    }
}
