package com.speedrun.domain.navigation

import com.speedrun.domain.dashboard.navigation.DashboardNavigator
import com.speedrun.domain.feature.game.navigation.GameNavigator
import com.speedrun.domain.feature.leaderboards.navigation.LeaderboardNavigator
import com.speedrun.domain.feature.player.navigation.PlayerNavigator
import com.speedrun.domain.feature.run.navigation.RunNavigator

interface AppNavigator :
    DashboardNavigator,
    GameNavigator,
    LeaderboardNavigator,
    RunNavigator,
    PlayerNavigator
