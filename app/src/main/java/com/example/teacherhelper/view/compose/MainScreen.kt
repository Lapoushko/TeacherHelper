package com.example.teacherhelper.view.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.teacherhelper.presenter.MainActivityViewModel
import com.example.teacherhelper.repository.data.Group

/**
 * Контент основной активности
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(mainActivityViewModel: MainActivityViewModel = hiltViewModel(),
               onClick : (Group) -> Unit){
    mainActivityViewModel.loadGroups()
    Scaffold(
        content = {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = mainActivityViewModel.resultLiveGroups.value ?: emptyList(),
                    itemContent = {
                        GroupListItem(group = it,
                            onClick = onClick)
                    }
                )
            }
        }
    )
}
