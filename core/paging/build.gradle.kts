plugins {
    id("speedrun.domain.android.library")
}

dependencies{
    api(libs.androidx.paging.pagingCompose)
    api(projects.data.pagination)
}