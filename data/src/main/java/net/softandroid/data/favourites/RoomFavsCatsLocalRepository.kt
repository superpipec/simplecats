package net.softandroid.data.favourites

import net.softandroid.data.database.dao.CatsDao
import net.softandroid.data.database.entities.DbFavsCatsItem
import net.softandroid.domain.favourites.FavsCatsLocalRepository
import net.softandroid.domain.favourites.entity.FavsCatsItem

class RoomFavsCatsLocalRepository(
    private val catsDao: CatsDao
) : FavsCatsLocalRepository {

    override suspend fun save(favsCatsItem: FavsCatsItem) {
        catsDao.saveCat(favsCatsItem as DbFavsCatsItem)
    }

    override suspend fun getAll(): Collection<FavsCatsItem> {
        return catsDao.getAllCats()
    }
}