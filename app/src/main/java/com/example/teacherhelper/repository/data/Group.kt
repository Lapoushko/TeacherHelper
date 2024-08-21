package com.example.teacherhelper.repository.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.teacherhelper.util.Constants
import javax.inject.Inject

/**
 * Группа студентов
 *
 * @property id id группы
 * @property name имя группы
 * @property students список студентов
 * @property description описание группы
 */
@Entity(tableName = Constants.NAME_TABLE_GROUPS)
data class Group @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String
)

/**
 * Студента
 *
 * @property studentId id ученика
 * @property name имя ученика
 * @property description описание ученика
 * @author lapoushko
 */
@Entity
data class Student @Inject constructor(
    @PrimaryKey(autoGenerate = true) val studentId: Int,
    val name: String,
    val description: String,
    val groupId: Int
)

/**
 * Связь один-к-многим
 */
data class StudentByGroup(
    @Embedded val group: Group,
    @Relation(
        parentColumn = "id",
        entityColumn = "groupId"
    )
    val students: List<Student>
)