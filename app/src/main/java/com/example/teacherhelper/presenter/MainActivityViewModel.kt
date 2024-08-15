package com.example.teacherhelper.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для MainScreen
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var groupsRepository: GroupsRepositoryImpl
    private val resultMutableGroups = MutableLiveData<List<Group>>()
    val resultLiveGroups: LiveData<List<Group>> = resultMutableGroups

    init {
        Log.e(Constants.LOG_TAG, "vm created")
    }

    override fun onCleared() {
        Log.e(Constants.LOG_TAG, "vm cleared")
        super.onCleared()
    }

    /**
     * Загрузка групп
     */
    fun loadGroups() {
        viewModelScope.launch {
            if (resultMutableGroups.value.isNullOrEmpty()) {
                val groups = groupsRepository.getGroups()
                if (groups.isNotEmpty()) {
                    Log.e(Constants.LOG_TAG, "vm load groups with count: ${groups.count()}")
                    resultMutableGroups.value = groups
                } else {
                    Log.e(Constants.LOG_TAG, "vm is empty")
                }
            }
        }
    }
}