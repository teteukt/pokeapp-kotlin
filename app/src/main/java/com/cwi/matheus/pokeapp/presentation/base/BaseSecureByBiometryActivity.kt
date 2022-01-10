package com.cwi.matheus.pokeapp.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.cwi.matheus.pokeapp.service.BiometryService

abstract class BaseSecureByBiometryActivity : AppCompatActivity() {

    private lateinit var biometryService: BiometryService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biometryService = BiometryService(
            this,
            onAuthenticationFail = { onAuthFail() },
            onAuthenticationSuccess = { onAuthSuccess() },
            onAuthenticationNotNeeded = { onAuthNotNeeded() })
        biometryService.callBiometricAuth()
    }

    abstract fun isSecured() : Boolean

    open fun onAuthFail() { onBackPressed() }

    open fun onAuthSuccess() {}

    open fun onAuthNotNeeded() {}
}