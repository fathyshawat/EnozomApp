package com.codingwithjks.roomdatabasewithflow.Dao

import androidx.room.*
import com.example.enozomapp.Model.Employee
import kotlinx.coroutines.flow.Flow


@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)

    @Query("SELECT * FROM employee ORDER BY id ASC")
    fun getEmployee(): Flow<List<Employee>>

    @Update
    suspend fun updateEmployee(employee: Employee): Int

    @Query("DELETE FROM employee WHERE id = :userId")
    suspend fun deleteByUserId(userId: Long)
}