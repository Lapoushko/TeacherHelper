@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.teacherhelper.view.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.teacherhelper.presenter.MainActivityViewModel
import com.example.teacherhelper.repository.data.Group

/**
 * Контент основной активности
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(mainActivityViewModel: MainActivityViewModel = hiltViewModel(),
               onGroupClick : (Group) -> Unit,
               onAddGroupClick: () -> Unit){
    mainActivityViewModel.loadGroups()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(title = {
                Text(text = "Список групп")
            },
                scrollBehavior = scrollBehavior
                )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(onClick = onAddGroupClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save icon")
            }
        }
    ){ padding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)

        ) {
            items(
                items = mainActivityViewModel.resultLiveGroups.value ?: emptyList(),
                itemContent = {
                    GroupListItem(group = it,
                        onClick = onGroupClick)
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview(mainActivityViewModel: MainActivityViewModel = hiltViewModel()){
    mainActivityViewModel.loadGroups()
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { FloatingActionButton(onClick = {
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Save icon")
        }
        }
    ){ padding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)

        ) {
            items(
                items = mainActivityViewModel.resultLiveGroups.value ?: emptyList(),
                itemContent = {
                    GroupListItem(group = it,
                        onClick = {})
                }
            )
        }
    }
}
