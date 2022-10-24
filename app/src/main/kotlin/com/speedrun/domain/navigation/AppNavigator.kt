package com.speedrun.domain.navigation

import com.speedrun.domain.dashboard.navigation.DashboardNavigator
import com.speedrun.domain.feature.game.navigation.GameNavigator

interface AppNavigator : DashboardNavigator, GameNavigator
