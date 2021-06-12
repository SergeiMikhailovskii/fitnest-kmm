package com.mikhailovskii.kmmtest.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikhailovskii.kmmtest.android.databinding.ActivityMainBinding
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.usecase.LoginUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        LoginUseCase().save(
            LoginData(
                binding.etLogin.text.toString(),
                binding.etPassword.text.toString()
            )
        )
    }
}
