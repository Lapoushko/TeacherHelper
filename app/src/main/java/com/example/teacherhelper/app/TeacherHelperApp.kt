package com.example.teacherhelper.app

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teacherhelper.view.Screen
import com.example.teacherhelper.view.compose.GroupDetailScreen
import com.example.teacherhelper.view.compose.MainScreen
import dagger.hilt.android.HiltAndroidApp

/**
 * Базовые настройки приложения
 */
@HiltAndroidApp
class TeacherHelperApp : Application() {

    @Composable fun TeacherHelperSet(){
        val navController = rememberNavController()
        TeacherHelperNavHost(navController = navController)
    }

    @Composable
    fun TeacherHelperNavHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = Screen.Main.route) {
            composable(route = Screen.Main.route){
                MainScreen{
                    navController.navigate(Screen.GroupDetail.route)
                }
            }
            composable(route = Screen.GroupDetail.route){
                GroupDetailScreen()
            }
        }
    }
}

