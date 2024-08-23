package com.example.teacherhelper.view

/**
 * Скрин для навигации
 * @param route маршрут скрина
 */
sealed class Screen(
    val route: String,
) {
    /**
     * Скрин главного экрана
     */
    data object Main : Screen(route = "main")

    /**
     * детали группы
     */
    data object GroupDetail : Screen(
        route = "groupDetail/{groupId}"
    ) {
        /**
         * Создать нужный маршрут
         * @param groupId айди нужной группы
         */
        fun createRoute(groupId: Int?) = "groupDetail/$groupId"
    }

    /**
     * Создать нового студента
     */
    data object CreateStudent : Screen(
        route = "createStudent/{groupId}"
    ) {
        /**
         * Создать нужный маршрут
         * @param groupId айди нужной группы
         */
        fun createRoute(groupId: Int?) = "createStudent/$groupId"
    }

    /**
     * Создать новую группу
     */
    data object CreateGroup : Screen(
        route = "createGroup"
    )

    /**
     * Скрин редактирования группы
     */
    data object EditGroup : Screen(
        route = "editGroup/{groupId}"
    ) {
        /**
         * Создать нужный маршрут
         * @param groupId айди нужной группы
         */
        fun createRoute(groupId: Int?) = "editGroup/$groupId"
    }
}