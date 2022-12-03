package com.example.enozomapp.Repository

import com.example.enozomapp.Model.Employee

interface UpdateInsertEmployee {

    suspend fun insert(employee: Employee)
    suspend fun update(employee: Employee)


}