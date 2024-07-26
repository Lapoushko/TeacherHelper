package com.example.teacherhelper.repository.data

/**
 * Пример группы
 */
class ExampleData {
    private val student1: Student = Student(0, "Вадя","Двоечник")
    private val student2: Student = Student(1, "Настя","Отличница")
    val groups: List<Group> = listOf(Group(0,
        "ЕГЭ Информатика",
        listOf(student1,student2),
        "ЕГЭшники"),
        (
                Group(1,
            "ОГЭ Информатика",
            listOf(student1,student2),
            "ОГЭшники")),
        Group(2,
            "ОГЭ Информатика",
            listOf(student1,student2),
            "ОГЭшники")
    )
}