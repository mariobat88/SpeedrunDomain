package com.speedrun.domain.dashboard.feature.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.speedrun.domain.core.navigation.Destination
import com.speedrun.domain.dashboard.feature.search.SearchScreen

object SearchNavigation : Destination {
    override val route = "dashboard/search_route"
    override val destination: String? = null
}

fun NavGraphBuilder.searchNavigation(searchNavigator: SearchNavigator) {
    composable(route = SearchNavigation.route) {
        SearchScreen(searchNavigator)
    }
}
