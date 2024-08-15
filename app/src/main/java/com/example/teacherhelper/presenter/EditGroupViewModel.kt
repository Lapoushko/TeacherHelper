package com.example.teacherhelper.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * VM для реадктирования группы
 */
@HiltViewModel
class EditGroupViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var groupsRepository: GroupsRepositoryImpl

    init {
        Log.e(Constants.LOG_TAG, "init editor group vm")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "create editor group vm cleared")
        super.onCleared()
    }

    /**
     * Редактирование группы
     */
    fun editGroup(groupId: Int, name: String, description: String) {
        viewModelScope.launch {
            if (name.isNotEmpty() && description.isNotEmpty()) {
                val group = Group(
                    id = groupsRepository.getGroup(groupId).id,
                    name = name,
                    students = groupsRepository.getGroup(groupId).students,
                    description = description
                )
                groupsRepository.groupService.editGroup(group)
            }
        }
    }
}