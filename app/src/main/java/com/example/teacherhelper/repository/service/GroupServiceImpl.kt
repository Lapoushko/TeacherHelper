package com.example.teacherhelper.repository.service

import com.example.teacherhelper.repository.dao.GroupDao
import com.example.teacherhelper.repository.data.Group
import javax.inject.Inject

/**
 * Сервис, отвечающий за работу с группой студентов
 */
class GroupServiceImpl @Inject constructor(dao: GroupDao) : GroupService {

    private val groupDao = dao

    override fun getGroup(id: Int): Group {
        return groupDao.getGroup(id)
    }

    override fun getGroups(): List<Group> {
        return groupDao.getGroups()
    }
}