package com.example.teacherhelper.repository.dao

import com.example.teacherhelper.repository.data.ExampleData
import com.example.teacherhelper.repository.data.Group
//TODO подключить базу данных для работы с данными
/**
 * Дао для работы с группой
 */
class GroupDao {
    private var data: ExampleData = ExampleData()

    /**
     * Получить группу
     * @param id номер группы
     */
    fun getGroup(id: Int): Group{
        return data.groups[id]
    }

    /**
     * Получить группы
     */
    fun getGroups(): List<Group>{
        return data.groups
    }
}