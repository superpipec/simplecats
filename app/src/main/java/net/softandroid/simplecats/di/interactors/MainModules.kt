package net.softandroid.simplecats.di.interactors

import net.softandroid.data.main.ApiGetCatsListErrorHandler
import net.softandroid.data.main.ApiGetCatsListRepository
import net.softandroid.domain.main.GetCatsListRepository
import net.softandroid.domain.main.GetCatsListUseCase
import org.koin.dsl.module

val mainModules = module {

    factory<GetCatsListRepository> {
        ApiGetCatsListRepository(get(), get())
    }

    factory {
        GetCatsListUseCase(
            get(),
            ApiGetCatsListErrorHandler()
        )
    }

}