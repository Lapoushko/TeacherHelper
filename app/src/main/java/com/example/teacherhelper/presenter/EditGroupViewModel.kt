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
 * @param groupsRepository репозиторий для работы с данными
 */
@HiltViewModel
class EditGroupViewModel @Inject constructor(
    private val groupsRepository: GroupsRepositoryImpl
) : ViewModel() {
    init {
        Log.e(Constants.LOG_TAG, "init editor group vm")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "create editor group vm cleared")
        super.onCleared()
    }

    /**
     * Редактирование группы
     * @param groupId айди группы
     * @param name новое имя группы
     * @param description новое описание группы
     */
    fun editGroup(groupId: Int, name: String, description: String) {
        viewModelScope.launch {
            if (name.isNotEmpty() && description.isNotEmpty()) {
                val group = Group(
                    id = groupId,
                    name = name,
                    description = description
                )
                groupsRepository.updateGroup(group)
            }
        }
    }
}