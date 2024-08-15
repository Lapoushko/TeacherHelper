package com.example.teacherhelper.repository.service

import com.example.teacherhelper.repository.dao.StudentDao
import com.example.teacherhelper.repository.data.Student
import javax.inject.Inject

/**
 * Сервис, отвечающий за работу со студентом
 */
class StudentServiceImpl @Inject constructor(dao: StudentDao) : StudentService {
    private val studentDao = dao

    override fun getStudent(id: Int): Student {
        return studentDao.getStudent(id)
    }
}