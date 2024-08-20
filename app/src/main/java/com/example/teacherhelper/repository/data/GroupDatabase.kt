package com.example.teacherhelper.repository.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherhelper.repository.dao.GroupDao
import com.example.teacherhelper.util.Constants

//@Database(
//    entities = [
//        Group::class, Student::class
//    ],
//    version = Constants.VERSION_DATABASE
//)
/**
 * База данных группы
 */
abstract class GroupDatabase : RoomDatabase(){
    abstract val dao: GroupDao
    companion object{
        fun createDatabase(context: Context): GroupDatabase{
            return Room.databaseBuilder(
                context = context,
                GroupDatabase::class.java,
                Constants.NAME_DATABASE
            ).build()
        }
    }
}