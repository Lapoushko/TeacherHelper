package com.example.teacherhelper.app

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teacherhelper.view.Screen
import com.example.teacherhelper.view.compose.CreatorNewGroupScreen
import com.example.teacherhelper.view.compose.CreatorNewStudentScreen
import com.example.teacherhelper.view.compose.EditGroupScreen
import com.example.teacherhelper.view.compose.GroupDetailScreen
import com.example.teacherhelper.view.compose.MainScreen
import dagger.hilt.android.HiltAndroidApp

/**
 * Базовые настройки приложения
 */
@HiltAndroidApp
class TeacherHelperApp : Application() {

    @Composable
    fun TeacherHelperSet() {
        val navController = rememberNavController()
        TeacherHelperNavHost(navController = navController)
    }

    @Composable
    fun TeacherHelperNavHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = Screen.Main.route) {
            composable(route = Screen.Main.route) {
                MainScreen(
                    onGroupClick = { group ->
                        navController.navigate(
                            Screen.GroupDetail.createRoute(groupId = group.id)
                        )
                    },
                    onAddGroupClick = {
                        navController.navigate(
                            Screen.CreateGroup.route
                        )
                    },
                    onEditGroupClick = { group ->
                        navController.navigate(
                            Screen.EditGroup.createRoute(groupId = group.id)
                        )
                    }
                )
            }

            composable(
                route = Screen.GroupDetail.route
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("groupId")?.toInt()
                    ?: 0
                GroupDetailScreen(
                    onMainClick = onClickMain(navController),
                    onAddStudentClick = {
                        navController.navigate(
                            Screen.CreateStudent.createRoute(groupId = id)
                        )
                    },
                    groupId = id
                )
            }

            composable(
                route = Screen.CreateStudent.route
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("groupId")?.toInt()
                    ?: 0
                CreatorNewStudentScreen(
                    onBackClick = onClickMain(navController),
                    groupId = id
                )
            }

            composable(
                route = Screen.CreateGroup.route
            ) {
                CreatorNewGroupScreen(
                    onBackClick = onClickMain(navController)
                )
            }

            composable(
                route = Screen.EditGroup.route
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("groupId")?.toInt()
                    ?: 0
                EditGroupScreen(
                    onBackClick = onClickMain(navController),
                    groupId = id
                )
            }
        }
    }

    /**
     * Клик на основной экран
     */
    private fun onClickMain(navController: NavHostController): () -> Unit {
        return { navController.navigate(Screen.Main.route) }
    }
}


