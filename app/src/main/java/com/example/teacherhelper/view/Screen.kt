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

    /**
     * Скрин редактирования студента
     */
    data object EditStudent : Screen(
        route = "editStudent/{groupId}/{studentId}"
    ) {
        /**
         * Создать нужный маршрут
         * @param groupId айди нужной группы
         * @param groupId айди нужного студента
         */
        fun createRoute(groupId: Int?, studentId: Int?) = "editStudent/$groupId/$studentId"
    }
}