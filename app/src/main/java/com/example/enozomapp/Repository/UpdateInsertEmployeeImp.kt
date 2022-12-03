package com.example.enozomapp.Repository

import androidx.annotation.WorkerThread
import com.codingwithjks.roomdatabasewithflow.Dao.EmployeeDao
import com.example.enozomapp.Model.Employee
import javax.inject.Inject

class UpdateInsertEmployeeImp @Inject constructor(private val employeeDao: EmployeeDao) :
    UpdateInsertEmployee {

    @WorkerThread
    override suspend fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }

    @WorkerThread
    override suspend fun update(employee: Employee) {
        employeeDao.updateEmployee(employee)
    }
}