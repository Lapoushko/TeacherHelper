package com.example.teacherhelper.view.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.teacherhelper.app.TeacherHelperApp
import com.example.teacherhelper.ui.theme.TeacherHelperTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Первая activity
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val mainViewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.e("ViewM", mainViewModel.toString())
        setContent {
            TeacherHelperTheme {
                TeacherHelperApp().TeacherHelperSet()
            }
        }
    }
}