package alphainterplanetary.tasks.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import alphainterplanetary.tasks.ui.add_edit_task.AddEditTaskScreen
import alphainterplanetary.tasks.ui.home.HomeScreen
import alphainterplanetary.tasks.ui.settings.SettingsScreen
import alphainterplanetary.tasks.ui.share_task.ShareTaskScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onAddTask = { navController.navigate("add_edit_task/-1") },
                onTaskClick = { navController.navigate("add_edit_task/${it.id}") },
                onSettingsClick = { navController.navigate("settings") }
            )
        }
        composable(
            "add_edit_task/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            AddEditTaskScreen(
                onSave = { navController.popBackStack() },
                onDelete = { navController.popBackStack() },
                onShare = { navController.navigate("share_task/$taskId") }
            )
        }
        composable("settings") {
            SettingsScreen()
        }
        composable(
            "share_task/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) {
            ShareTaskScreen()
        }
    }
}
