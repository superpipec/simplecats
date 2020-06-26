package net.softandroid.data.main

import net.softandroid.domain.common.UseCaseErrorHandler
import net.softandroid.domain.main.GetCatsListError
import java.net.UnknownHostException

class ApiGetCatsListErrorHandler: UseCaseErrorHandler<GetCatsListError> {

    override fun handle(throwable: Throwable): GetCatsListError? {

        // here can be handled any server exceptions
        // add gson: Gson() to constructor, and move it to handleServerException for parse
        // for example I leave it as is
        if (throwable is UnknownHostException) {
            return handleServerException(throwable)
        }
        return null
    }

    private fun handleServerException(error: UnknownHostException): GetCatsListError {
        // when (error) = {

        // }
        // else
        return GetCatsListError.NetworkError
    }
}