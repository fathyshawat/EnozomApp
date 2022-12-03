package com.example.enozomapp.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.enozomapp.Model.Employee
import com.example.enozomapp.R
import com.example.enozomapp.ViewModel.AddEmployeeViewModel
import com.example.enozomapp.databinding.AddEmployeeBinding
import com.example.enozomapp.utils.Utils
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream


@AndroidEntryPoint
class AddUpdateEmployeeFragment : Fragment() {

    private lateinit var binding: AddEmployeeBinding
    private var imageByte: ByteArray? = null
    private val mainViewModel by viewModels<AddEmployeeViewModel>()

    private val skillsList = listOf(
        "Android",
        "Ios",
        "ASP.NET",
        "PHP"
    )

    private val selectedSkills = ArrayList<String>()
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                if (!it.value) {
                    Toast.makeText(
                        requireActivity(),
                        "Approve All permissions",
                        Toast.LENGTH_LONG
                    ).show()
                    return@forEach
                }
                moveToPickImage()
            }
        }

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        val iStream: InputStream? =
            requireActivity().contentResolver.openInputStream(firstImage.uri)
        imageByte = iStream?.let { it1 -> Utils.getBytes(it1) }
        binding.image.setImageURI(firstImage.uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddEmployeeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        initEvent()


    }

    private fun permission() {

        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
    }

    private fun setAdapter() {
        val adapter = ArrayAdapter(requireActivity(), R.layout.item_drop_down, skillsList)
        binding.tietSkills.setAdapter(adapter)
    }

    private fun initEvent() {
        binding.tietSkills.setOnItemClickListener { parent, _, position, _ ->
            val selectedSkills = parent.getItemAtPosition(position) as String
            if (this.selectedSkills.contains(selectedSkills)) {
                Toast.makeText(
                    requireActivity(),
                    "You have already selected $selectedSkills",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                addSkillChip(selectedSkills)
            }
            binding.tietSkills.setText("")
        }

        binding.add.setOnClickListener {
            if (binding.employeeNameEd.text.toString().isEmpty()) {
                binding.employeeName.error = "this is field required"
                return@setOnClickListener
            }
            insert()
        }

        binding.image.setOnClickListener {
            permission()
        }
    }

    private fun insert() {
        val employee = Employee()
        employee.skills = selectedSkills
        employee.email = binding.employeeEmailEd.text.toString()
        employee.name = binding.employeeNameEd.text.toString()
        employee.image = imageByte
        mainViewModel.insert(employee)
    }

    private fun getChip(name: String): Chip {
        return Chip(requireActivity()).apply {
            text = name
            isCloseIconVisible = true
            setOnCloseIconClickListener {
                (it.parent as ChipGroup).removeView(it)
            }
        }
    }

    private fun addSkillChip(planetName: String) {
        selectedSkills.add(planetName)
        binding.cgTags.addView(getChip(planetName))

    }

    private fun moveToPickImage() {
        imagePickerLauncher.launch(
            ImagePickerConfig {
                mode = ImagePickerMode.SINGLE
                returnMode = ReturnMode.ALL
                isFolderMode = true
                folderTitle = "Folder"
                imageTitle = "Tap to select"
                doneButtonText = "DONE"
            }
        )
    }


}