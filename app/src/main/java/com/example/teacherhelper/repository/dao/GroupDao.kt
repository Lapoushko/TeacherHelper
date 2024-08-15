package com.example.teacherhelper.repository.dao

import com.example.teacherhelper.repository.data.ExampleData
import com.example.teacherhelper.repository.data.Group
import javax.inject.Inject

//TODO подключить базу данных для работы с данными
/**
 * Дао для работы с группой
 */
class GroupDao @Inject constructor() {
    private var data: ExampleData = ExampleData()

    /**
     * Получить группу
     * @param id номер группы
     */
    fun getGroup(id: Int): Group {
        return data.groups[id]
    }

    /**
     * Получить группы
     */
    fun getGroups(): List<Group> {
        return data.groups
    }

    /**
     * Сохранить группу
     */
    fun saveGroup(group: Group) {
        if (data.groups.isNotEmpty()) {
            data.groups[group.id] = group
        } else {
            data.groups.add(group)
        }
    }

    /**
     * Добавить новую группу
     */
    fun addGroup(group: Group) {
        data.groups.add(group)
    }
}