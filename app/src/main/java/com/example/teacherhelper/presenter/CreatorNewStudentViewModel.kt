package com.example.teacherhelper.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Student
import com.example.teacherhelper.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model для добавления нового студента в группу
 */
@HiltViewModel
class CreatorNewStudentViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var groupsRepository: GroupsRepositoryImpl

    init {
        Log.e(Constants.LOG_TAG,"init creator student vm")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG,"creator student vm cleared")
        super.onCleared()
    }

    /**
     * Добавить студента в группу
     * @param groupId groupId
     */
    fun addStudent(groupId: Int, name: String, description: String){
        viewModelScope.launch {
            val student = Student(groupsRepository.getGroup(groupId).students.size,
                name, description)
            if (student.name.isNotEmpty() && student.description.isNotEmpty()){
                groupsRepository.groupService.addStudent(
                    groupsRepository.getGroup(groupId),
                    student
                )
            }
        }
    }
}