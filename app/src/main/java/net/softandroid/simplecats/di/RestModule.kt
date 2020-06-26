package net.softandroid.simplecats.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import net.softandroid.data.rest.OkHttpFactory
import net.softandroid.data.rest.api.CatsApi
import net.softandroid.data.rest.api.RetrofitFactory
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

const val HOST = "https://api.thecatapi.com/"

val restModule = module {
    single { OkHttpFactory().create() }

    single { GsonBuilder().create() }

    factory<CatsApi> {
        RetrofitFactory(
            get(),
            HOST,
            GsonConverterFactory.create(get()),
            CoroutineCallAdapterFactory()
        ).create().create(CatsApi::class.java)
    }
}