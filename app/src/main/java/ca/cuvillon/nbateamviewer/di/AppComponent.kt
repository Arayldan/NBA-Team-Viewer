package ca.cuvillon.nbateamviewer.di

import ca.cuvillon.local.di.localModule
import ca.cuvillon.teamlist.di.featureTeamListModule

val appComponent = listOf(localModule, featureTeamListModule)
