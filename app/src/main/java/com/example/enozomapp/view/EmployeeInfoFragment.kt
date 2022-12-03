package com.example.enozomapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enozomapp.Model.Employee
import com.example.enozomapp.R
import com.example.enozomapp.ViewModel.EmployeeInfoViewModel
import com.example.enozomapp.databinding.EmployeeInfoBinding
import com.example.enozomapp.utils.ClickListener
import com.example.enozomapp.view.adapter.EmployeeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeInfoFragment : Fragment(), ClickListener {

    private lateinit var binding: EmployeeInfoBinding
    private val mainViewModel by viewModels<EmployeeInfoViewModel>()
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var employeeLs: ArrayList<Employee>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmployeeInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        employeeLs = ArrayList()
        initEvent()
        initViewModel()
    }

    private fun initViewModel() {
        mainViewModel.getEmployee.observe(requireActivity(), Observer { response ->
            employeeLs.clear()
            employeeLs = response as ArrayList<Employee>
            employeeAdapter.setEmployee(employeeLs)
        })
    }

    private fun initEvent() {
        binding.add.setOnClickListener {
            addEmployeeFragment()
        }
    }

    private fun initRecyclerView() {
        employeeAdapter = EmployeeAdapter(ArrayList())
        employeeAdapter.setClickListener(this)
        binding.employeeRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = employeeAdapter
        }
    }

    override fun click(pos: Int, v: View) {
        when (v.id) {
            R.id.edit -> {
                editFragment(employeeLs[pos])
            }
            R.id.delete -> {
                mainViewModel.delete(employeeLs[pos].id.toLong())
            }
        }
    }

    private fun editFragment(employee: Employee) {
        val bundle = Bundle()
        bundle.putParcelable("employee", employee)
        bundle.putBoolean("fromEdit", true)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val f = AddUpdateEmployeeFragment()
        f.arguments = bundle
        transaction?.replace(R.id.frame_layout, f)
        transaction?.addToBackStack("info")
        transaction?.commit()
    }

    private fun addEmployeeFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val f = AddUpdateEmployeeFragment()
        transaction?.replace(R.id.frame_layout, f)
        transaction?.addToBackStack("info")
        transaction?.commit()
    }

}