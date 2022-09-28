plugins {
    id("speedrun.domain.java.library")
}

dependencies{
    api(projects.networking.api.common)
    api(projects.data.repo.common)
}
