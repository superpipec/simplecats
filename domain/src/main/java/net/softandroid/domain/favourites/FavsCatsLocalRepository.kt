package net.softandroid.domain.favourites

import net.softandroid.domain.favourites.entity.FavsCatsItem

interface FavsCatsLocalRepository {

    suspend fun save(favsCatsItem: FavsCatsItem)

    suspend fun getAll(): Collection<FavsCatsItem>
}