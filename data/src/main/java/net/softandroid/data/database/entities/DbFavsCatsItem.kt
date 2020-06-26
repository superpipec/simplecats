package net.softandroid.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.softandroid.domain.favourites.entity.FavsCatsItem

internal const val CATS_TABLE = "cats"

@Entity(tableName = CATS_TABLE)
data class DbFavsCatsItem(
    @PrimaryKey
    override val id: String,
    override val height: Int,
    override val url: String,
    override val width: Int
): FavsCatsItem