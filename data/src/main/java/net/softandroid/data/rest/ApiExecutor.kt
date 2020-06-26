package net.softandroid.data.rest

import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Converts REST API call to safe result.
 *
 * */
@Throws(
    NullPointerException::class,
    SocketTimeoutException::class,
    UnknownHostException::class
)
suspend fun <T> safeApiCall(
    call: suspend () -> Response<T>
): T {
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()?.let { body ->
                body
            } ?: throw NullPointerException()
        }
        // Can be added custom exception for handle server error codes, or other. Removed for example
        throw UnknownHostException(response.message())
    } catch (ex: Throwable) {
        throw ex
    }
}