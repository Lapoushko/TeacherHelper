package com.example.teacherhelper.view

/**
 * Скрин для навигации
 */
sealed class Screen(
    val route: String,
) {
    data object Main : Screen("main")

    data object GroupDetail : Screen(
        route = "groupDetail/{groupId}"
    ) {
        fun createRoute(groupId: Int) = "groupDetail/$groupId"
    }

    data object CreateStudent : Screen(
        route = "createStudent/{groupId}"
    ) {
        fun createRoute(groupId: Int) = "createStudent/$groupId"
    }

    data object CreateGroup : Screen(
        route = "createGroup"
    )

    data object EditGroup : Screen(
        route = "editGroup/{groupId}"
    ) {
        fun createRoute(groupId: Int) = "editGroup/$groupId"
    }
}