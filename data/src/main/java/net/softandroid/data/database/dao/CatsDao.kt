package net.softandroid.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.softandroid.data.database.entities.CATS_TABLE
import net.softandroid.data.database.entities.DbFavsCatsItem

@Dao
interface CatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCat(dbFavsCatsItem: DbFavsCatsItem)

    @Query("select * FROM $CATS_TABLE")
    fun getAllCats(): List<DbFavsCatsItem>
}