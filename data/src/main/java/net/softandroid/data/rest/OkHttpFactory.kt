package net.softandroid.data.rest

import okhttp3.OkHttpClient


class OkHttpFactory(
) {

    fun create(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }

}
