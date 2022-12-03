package com.codingwithjks.roomdatabasewithflow.di

import android.content.Context
import androidx.room.Room
import com.codingwithjks.roomdatabasewithflow.Dao.EmployeeDao
import com.example.enozomapp.Database.EmployeeDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule  {

    @Provides
    fun providesEmployeeDao(employeeDatabase: EmployeeDatabase):EmployeeDao = employeeDatabase.employeeDao()

    @Provides
    @Singleton
    fun providesEmployeeDatabase(@ApplicationContext context: Context):EmployeeDatabase
            = Room.databaseBuilder(context,EmployeeDatabase::class.java,"EmployeeDatabase").build()

}