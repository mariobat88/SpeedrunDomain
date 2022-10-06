plugins {
    id("speedrun.domain.android.feature")
}

android{
    ksp {
        arg("compose-destinations.moduleName", "game")
        arg("compose-destinations.mode", "destinations")
    }
}
