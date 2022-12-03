package com.example.enozomapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.enozomapp.Model.Employee
import com.example.enozomapp.Repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeInfoViewModel
@Inject constructor(private val repository: EmployeeRepository) : ViewModel() {

    val getEmployee: LiveData<List<Employee>>
        get() =
            repository.getEmployee().flowOn(Dispatchers.Main)
                .asLiveData(context = viewModelScope.coroutineContext)


    fun delete(id: Long){
        viewModelScope.launch {
            repository.delete(id)
        }
    }

}