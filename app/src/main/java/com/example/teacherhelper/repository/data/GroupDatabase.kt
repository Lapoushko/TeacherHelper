package com.example.teacherhelper.repository.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherhelper.repository.dao.Dao
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


@Database(
    entities = [
        Group::class,
        Student::class
    ],
    version = 2
)
abstract class GroupDatabase : RoomDatabase(){
    abstract fun dao(): Dao
    companion object{
        fun createDatabase(context: Context): GroupDatabase{
            return Room.databaseBuilder(
                context = context,
                GroupDatabase::class.java,
                Constants.NAME_DATABASE
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}