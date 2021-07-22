package com.site_valley.biometric

import android.hardware.biometrics.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.*

import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    lateinit var btnauth :Button
    lateinit var tvauth: TextView
    lateinit var executor: Executor
    lateinit var biometricprompt: BiometricPrompt
    lateinit var promptInfo: BiometricPrompt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        promptInfo = getMainExecutor(this)
        btnauth= findViewById(R.id.button)
        tvauth = findViewById(R.id.authtext)
        executor = getMainExecutor(this)
        BiometricPrompt(this@MainActivity, executor, object :BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                tvauth.text= "error$errString"
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                tvauth.text = "failed"
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                tvauth.text = "congratulation"
            }
        }).also { biometricprompt = it }

    }
}