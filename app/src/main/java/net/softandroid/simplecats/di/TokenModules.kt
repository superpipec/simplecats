package net.softandroid.simplecats.di

import net.softandroid.data.auth.BaseTokenRepository
import net.softandroid.domain.auth.TokenRepository
import org.koin.dsl.module

val tokenModules = module {

    factory<TokenRepository> {
        BaseTokenRepository()
    }

}