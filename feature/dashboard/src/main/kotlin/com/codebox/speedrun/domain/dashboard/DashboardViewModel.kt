package com.codebox.speedrun.domain.dashboard

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class DashboardViewModel : ViewModel() {

    companion object {
        @Composable
        fun create(): DashboardViewModel {
            val factory = viewModelFactory { initializer { DashboardViewModel() } }
            return factory.create(DashboardViewModel::class.java, CreationExtras.Empty)
        }
    }

    init {
        Log.d("BATBAT", "DashboardViewModel")
    }
}