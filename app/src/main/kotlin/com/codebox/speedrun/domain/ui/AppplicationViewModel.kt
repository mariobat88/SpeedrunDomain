package com.codebox.speedrun.domain.ui

import androidx.lifecycle.ViewModel
import com.codebox.speedrun.domain.core.navigation.MainNavigator
import javax.inject.Inject

class AppplicationViewModel @Inject constructor(
    val mainNavigator: MainNavigator
) : ViewModel() {

}