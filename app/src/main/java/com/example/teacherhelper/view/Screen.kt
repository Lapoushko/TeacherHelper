package com.example.teacherhelper.view

import androidx.navigation.NamedNavArgument

/**
 * Скрин для навигации
 */
sealed class Screen(
    val route: String,
    val navArgument: List<NamedNavArgument> = emptyList()
){
    data object Main : Screen("main")

    data object GroupDetail : Screen("group detail")

    data object StudentDetail : Screen("student detail")
}