package com.example.teacherhelper.view.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.teacherhelper.app.TeacherHelperApp
import com.example.teacherhelper.ui.theme.TeacherHelperTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Основная activity
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeacherHelperTheme {
                TeacherHelperApp().TeacherHelperSet()
            }
        }
    }
}