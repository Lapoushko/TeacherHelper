package com.example.teacherhelper.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherhelper.repository.GroupsRepository
import com.example.teacherhelper.repository.GroupsRepositoryImpl
import com.example.teacherhelper.repository.data.Group
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val LOG_TAG = "ViewM"

/**
 * ViewModel для MainScreen
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(): ViewModel() {
    private val groupsRepository: GroupsRepository = GroupsRepositoryImpl()

    private val resultMutableGroups = MutableLiveData<List<Group>>()
    val resultLiveGroups: LiveData<List<Group>> = resultMutableGroups

    init {
        loadGroups()
        Log.e(LOG_TAG,"vm created")
    }

    override fun onCleared() {
        Log.e(LOG_TAG,"vm cleared")
        super.onCleared()
    }

    /**
     * Загрузка групп
     */
    private fun loadGroups(){
        viewModelScope.launch {
            val groups = groupsRepository.getGroups()
            if (groups.isNotEmpty()){
                Log.e(LOG_TAG, "vm load groups with count: ${groups.count()}")
                resultMutableGroups.value = groups
            } else{
                Log.e(LOG_TAG, "vm is empty")
            }
        }
    }
}