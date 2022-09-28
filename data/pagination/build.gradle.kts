plugins {
    id("speedrun.domain.java.library")
}

dependencies{
    api(projects.networking.api.pagination)
    implementation(projects.data.datasource.common)
    implementation(projects.data.repo.common)
}
