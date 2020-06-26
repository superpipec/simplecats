package net.softandroid.data.favourites

import net.softandroid.domain.common.UseCaseErrorHandler
import net.softandroid.domain.favourites.FavsCatsError
import java.net.UnknownHostException

class FavsCatsErrorHandler: UseCaseErrorHandler<FavsCatsError> {

    override fun handle(throwable: Throwable): FavsCatsError? {

        // here can be handled any server exceptions
        // add gson: Gson() to constructor, and move it to handleServerException for parse
        // for example I leave it as is
        if (throwable is UnknownHostException) {
            return handleServerException(throwable)
        }
        return null
    }

    private fun handleServerException(error: UnknownHostException): FavsCatsError {
        // when (error) = {

        // }
        // else
        return FavsCatsError.StubError
    }
}