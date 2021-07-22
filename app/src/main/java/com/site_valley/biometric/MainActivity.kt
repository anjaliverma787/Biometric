package com.site_valley.biometric

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationResult
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor


class MainActivity : AppCompatActivity() {
    lateinit var btnauth :Button
    lateinit var tvauth: TextView
    lateinit var executor: Executor
    lateinit var biometricprompt: BiometricPrompt
    lateinit var promptInfo: PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnauth = findViewById(R.id.button)
        tvauth = findViewById(R.id.authtext)
        executor = ContextCompat.getMainExecutor(this)

        biometricprompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    tvauth.text = "error$errString"

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    tvauth.text = "failed"
                }

                override fun onAuthenticationSucceeded(result: AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    tvauth.text = "congratulation"

                }
            })



        promptInfo =
            PromptInfo.Builder().setTitle("Auth").setSubtitle("").setNegativeButtonText("cancel")
                .build()


        btnauth.setOnClickListener {

            biometricprompt.authenticate(promptInfo)

        }


    }


}