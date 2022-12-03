package com.example.enozomapp.Repository

import com.example.enozomapp.Model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {

    fun getEmployee(): Flow<List<Employee>>
    suspend fun delete(id: Long)
}