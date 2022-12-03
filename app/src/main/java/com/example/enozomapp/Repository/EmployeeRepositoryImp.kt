package com.example.enozomapp.Repository

import androidx.annotation.WorkerThread
import com.codingwithjks.roomdatabasewithflow.Dao.EmployeeDao
import com.example.enozomapp.Model.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepositoryImp @Inject constructor (private val employeeDao: EmployeeDao):EmployeeRepository {


    override fun getEmployee(): Flow<List<Employee>> {
        return employeeDao.getEmployee()
    }

    @WorkerThread
    override suspend fun delete(id: Long)  = withContext(Dispatchers.IO) {
        employeeDao.deleteByUserId(id)
    }

}