package net.softandroid.simplecats.di

import net.softandroid.data.database.SimpleCatsDatabase
import net.softandroid.data.database.buildDatabase
import net.softandroid.data.database.wrapper.DbFavsCatsItemWrapper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single { buildDatabase(androidApplication().applicationContext) }

    factory { get<SimpleCatsDatabase>().catsDao() }

    factory { DbFavsCatsItemWrapper() }
}