plugins {
    id("speedrun.domain.java.library")
}

dependencies{
    api(projects.networking.api.pagination)
    implementation(projects.networking.api.common)
    implementation(projects.data.repo.common)
}
