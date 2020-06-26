package net.softandroid.simplecats.di.interactors

import net.softandroid.data.favourites.FavsCatsErrorHandler
import net.softandroid.data.favourites.RoomFavsCatsLocalRepository
import net.softandroid.domain.favourites.FavsCatsLocalRepository
import net.softandroid.domain.favourites.FavsCatsUseCase
import org.koin.dsl.module

val favsModule = module {

    factory<FavsCatsLocalRepository> {
        RoomFavsCatsLocalRepository(get())
    }

    factory {
        FavsCatsUseCase(
            get(),
            FavsCatsErrorHandler()
        )
    }

}