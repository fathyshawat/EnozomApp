package com.example.enozomapp.di

import com.example.enozomapp.Repository.EmployeeRepository
import com.example.enozomapp.Repository.EmployeeRepositoryImp
import com.example.enozomapp.Repository.UpdateInsertEmployee
import com.example.enozomapp.Repository.UpdateInsertEmployeeImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    @ViewModelScoped
    abstract fun bindEmployeeRepository(employeeRepository: EmployeeRepositoryImp): EmployeeRepository

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateInsertEmployee(employeeRepository: UpdateInsertEmployeeImp): UpdateInsertEmployee


}