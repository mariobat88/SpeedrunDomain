plugins {
    id("speedrun.domain.android.repo")
}

dependencies{
    api(projects.data.repo.categories)
    api(projects.data.repo.games)
}