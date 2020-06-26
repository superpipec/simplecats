package net.softandroid.data.rest.api


import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * [Retrofit] factory. Setup REST api client and return target instance.
 * */
class RetrofitFactory(
    private val okHttpClient: OkHttpClient,
    private val baseUrl: String,
    private val converterFactory: Converter.Factory,
    private val callAdapterFactory: CallAdapter.Factory
) {

    /**
     * @return [Retrofit] instance.
     * */
    fun create(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()

}
