package com.example.teacherhelper.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.teacherhelper.presenter.MainActivityViewModel
import com.example.teacherhelper.presenter.MainViewModelFactory
import com.example.teacherhelper.repository.data.Group
import com.example.teacherhelper.ui.theme.TeacherHelperTheme

class MainActivity : ComponentActivity() {
    private lateinit var vm: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(
            this, MainViewModelFactory(this))[MainActivityViewModel::class.java]

        var groups = listOf<Group>()
        vm.loadGroups()
        vm.resultLiveGroups.observe(this){
            groups = it
        }
        setContent {
            TeacherHelperTheme {
                // A surface container using the 'background' color from the theme
                SurfaceCreator(groups = groups)
            }
        }
    }
}
@Composable
fun SurfaceCreator(groups: List<Group>){
    Surface(
        color = MaterialTheme.colorScheme.background
    ){
        ContentCreator(groups = groups)
    } 
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContentCreator(groups: List<Group>){
    Scaffold(
        content = {
            MainContent(groups = groups)
        }

    )
}