package com.example.enozomapp.Database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codingwithjks.roomdatabasewithflow.Dao.EmployeeDao
import com.example.enozomapp.Model.Employee


@Database(entities = [Employee::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}
