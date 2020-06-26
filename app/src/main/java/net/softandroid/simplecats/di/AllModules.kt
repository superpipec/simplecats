package net.softandroid.simplecats.di


import net.softandroid.simplecats.base.download.FileDownloader
import net.softandroid.simplecats.di.interactors.favsModule
import net.softandroid.simplecats.di.interactors.mainModules
import net.softandroid.simplecats.ui.favourites.favouritesActivityModule
import net.softandroid.simplecats.ui.main.mainActivityModule
import net.softandroid.simpleqrscanner.base.coroutine.ApplicationDispatchers
import net.softandroid.simpleqrscanner.base.coroutine.DispatchersProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module


val appModules = module {
    single { ApplicationDispatchers() } bind DispatchersProvider::class
    factory { FileDownloader(androidContext()) }
}

// simple example
val allModules = listOf(
    appModules,
    restModule,
    mainModules,
    tokenModules,
    databaseModule,
    mainActivityModule,
    favsModule,
    favouritesActivityModule
)
