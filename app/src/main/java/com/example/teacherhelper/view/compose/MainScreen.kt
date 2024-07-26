package com.example.teacherhelper.view.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teacherhelper.presenter.MainActivityViewModel

/**
 * Контент основной активности
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//TODO Изменить viewModel() на hiltViewModel()
fun MainScreen(mainActivityViewModel: MainActivityViewModel = viewModel(),
               onClick : () -> Unit){
    Scaffold(
        content = {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = mainActivityViewModel.resultLiveGroups.value!!,
                    itemContent = {
                        GroupListItem(group = it){
                            onClick()
                        }
                    }
                )
            }
        }
    )
}
