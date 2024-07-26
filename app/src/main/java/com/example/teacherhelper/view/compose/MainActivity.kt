package com.example.teacherhelper.view.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.teacherhelper.app.TeacherHelperApp
import com.example.teacherhelper.presenter.MainActivityViewModel
import com.example.teacherhelper.ui.theme.TeacherHelperTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Первая activity
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: MainActivityViewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

//        var groups = listOf<Group>()
//        vm.resultLiveGroups.observe(this){
//            groups = it
//        }
        setContent {
            TeacherHelperTheme {
                TeacherHelperApp().TeacherHelperSet()
//                SurfaceCreator(groups = groups)
            }
        }
    }
}