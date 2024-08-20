package com.example.teacherhelper.repository.dao

import com.example.teacherhelper.repository.data.ExampleData
import com.example.teacherhelper.repository.data.Group
import javax.inject.Inject

//TODO подключить базу данных для работы с данными

//@Dao
//interface Dao{
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertGroupq(group: Group)
//
//    @Delete
//    suspend fun deleteGroupq(group: Group)
//
//    @Query("SELECT * FROM groups")
//    suspend fun getGroupsq() : StateFlow<List<Group>>
//}

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
            data.groups[group.id!!] = group
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

    /**
     * Удалить группу
     */
    fun deleteGroup(group: Group){
        data.groups.remove(group)
    }
}