package net.softandroid.domain.main

import net.softandroid.domain.main.entity.GetCatsItem

interface GetCatsListRepository {

    suspend fun getCatsList(page: Int? = 0): Collection<GetCatsItem>
}