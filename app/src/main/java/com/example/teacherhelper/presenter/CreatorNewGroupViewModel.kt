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
 * Создатель новой группы
 */
@HiltViewModel
class CreatorNewGroupViewModel @Inject constructor(
    private val groupsRepository: GroupsRepositoryImpl
) : ViewModel() {
    init {
        Log.e(Constants.LOG_TAG, "init creator group vm")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "creator group vm cleared")
        super.onCleared()
    }

    /**
     * Создать группу
     */
    fun addGroup(name: String, description: String) {
        viewModelScope.launch {
            val groups = groupsRepository.getGroups()
            if (name.isNotEmpty() && description.isNotEmpty()) {
                val group = Group(
                    id = 0,
                    name = name,
                    description = description
                )
                groupsRepository.insertGroup(group)

            }
        }
    }
}