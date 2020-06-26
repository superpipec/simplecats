package net.softandroid.data.database.wrapper

import net.softandroid.data.database.entities.DbFavsCatsItem
import net.softandroid.domain.main.entity.GetCatsItem

class DbFavsCatsItemWrapper {

    fun toDbFavsItem(getCatsItem: GetCatsItem) =
        DbFavsCatsItem(getCatsItem.id, getCatsItem.height, getCatsItem.url, getCatsItem.width)
}