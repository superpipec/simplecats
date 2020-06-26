package net.softandroid.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.softandroid.data.database.dao.CatsDao
import net.softandroid.data.database.entities.DbFavsCatsItem

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "cats_database"

/**
 * Application database root class.
 * */
@Database(
    entities = [DbFavsCatsItem::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class SimpleCatsDatabase : RoomDatabase() {
    /**
     * @return cats dao.
     * */
    abstract fun catsDao(): CatsDao

}

/**
 * Build database implementation.
 * */
fun buildDatabase(
    context: Context
): SimpleCatsDatabase = Room.databaseBuilder(
    context,
    SimpleCatsDatabase::class.java,
    DATABASE_NAME
).build()
