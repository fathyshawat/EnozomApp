package com.example.enozomapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enozomapp.Model.Employee
import com.example.enozomapp.Repository.UpdateInsertEmployee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEmployeeViewModel @Inject constructor(private val repository: UpdateInsertEmployee) : ViewModel() {


    fun insert(employee: Employee){
        viewModelScope.launch {
            repository.insert(employee)
        }
    }

    fun update(employee: Employee){
        viewModelScope.launch {
            repository.update(employee)
        }
    }


}