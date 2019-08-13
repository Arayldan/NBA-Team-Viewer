package ca.cuvillon.nbateamviewer.di

import ca.cuvillon.local.di.localModule
import ca.cuvillon.remote.di.createRemoteModule
import ca.cuvillon.repository.di.repositoryModule
import ca.cuvillon.teamlist.di.featureTeamListModule

private const val BASE_URL = "https://raw.githubusercontent.com/Arayldan/NBA-Team-Viewer/master/"

val appComponent = listOf(
    createRemoteModule(BASE_URL),
    localModule,
    repositoryModule,
    featureTeamListModule
)
