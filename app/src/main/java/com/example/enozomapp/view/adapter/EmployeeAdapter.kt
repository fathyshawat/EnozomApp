package com.example.enozomapp.view.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enozomapp.Model.Employee
import com.example.enozomapp.databinding.ItemEmployeeBinding
import com.example.enozomapp.utils.ClickListener
import com.google.gson.Gson


class EmployeeAdapter
constructor(
    private var employee: ArrayList<Employee>
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {
    private var viewClick: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemBinding =
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = employee.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employee.get(position)
        holder.bind(employee, viewClick)
    }

    fun setClickListener(clickListener: ClickListener) {
        viewClick = clickListener
    }


    class EmployeeViewHolder(private val itemBinding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(employee: Employee?, viewClick: ClickListener?) {

            itemBinding.email.text = employee?.email
            itemBinding.name.text = employee?.name
            val skills =
                Gson().toJson(employee?.skills).replace("[", "").replace("]", "")
                    .replace("\"", " ")
            itemBinding.skills.text = skills
            val bmp = BitmapFactory.decodeByteArray(employee?.image, 0, employee?.image?.let {
                it.size
            } ?: 0)
            itemBinding.imageEmployee.post(Runnable {
                    itemBinding.imageEmployee.setImageBitmap(
                        Bitmap.createScaledBitmap(
                            bmp,
                            itemBinding.imageEmployee.width,
                            itemBinding.imageEmployee.height,
                            false
                        )
                    )})

            itemBinding.delete.setOnClickListener { v ->
                viewClick?.click(bindingAdapterPosition, v)
            }
            itemBinding.edit.setOnClickListener { v ->
                viewClick?.click(bindingAdapterPosition, v)
            }
        }


    }

    fun setEmployee(userList: ArrayList<Employee>) {
        this.employee = userList
        notifyDataSetChanged()
    }
}