package com.example.teacherhelper.view

import androidx.navigation.NamedNavArgument

/**
 * Скрин для навигации
 */
sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
){
    data object Main : Screen("main")

    data object GroupDetail : Screen(
        route = "groupDetail/{groupId}")
    {
        fun createRoute(groupId: Int) = "groupDetail/$groupId"
    }


    data object StudentDetail : Screen("student detail")
}