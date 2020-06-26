package net.softandroid.data.main

import net.softandroid.data.rest.api.CatsApi
import net.softandroid.data.rest.safeApiCall
import net.softandroid.domain.auth.TokenRepository
import net.softandroid.domain.main.GetCatsListRepository
import net.softandroid.domain.main.entity.GetCatsItem

class ApiGetCatsListRepository(
    private val tokenRepository: TokenRepository,
    private val catsApi: CatsApi
) : GetCatsListRepository {

    override suspend fun getCatsList(page: Int?): Collection<GetCatsItem> = safeApiCall {
        catsApi.getList(
            tokenRepository.getKey(),
            page
        )
    }
}