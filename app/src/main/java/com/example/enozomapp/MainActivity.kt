package com.example.enozomapp

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.enozomapp.databinding.MainActivityBinding
import com.example.enozomapp.view.AddUpdateEmployeeFragment
import com.example.enozomapp.view.EmployeeInfoFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchFragment()
    }

    private fun launchFragment(){
        val manager = supportFragmentManager
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val transaction = manager.beginTransaction()
        val f = EmployeeInfoFragment()
        transaction.replace(R.id.frame_layout, f)
        transaction.commit()
    }
}