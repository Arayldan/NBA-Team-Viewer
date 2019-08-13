package ca.cuvillon.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.cuvillon.local.dao.PlayerDao
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.model.entities.Player
import ca.cuvillon.model.entities.Team

@Database(entities = [Team::class, Player::class], version = AppDatabase.VERSION)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    //region DAO
    abstract fun teamDao(): TeamDao

    abstract fun playerDao(): PlayerDao
    //endregion

    companion object {
        internal const val VERSION = 1
        private const val NAME = "appDatabase.db"

        internal fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, NAME)
                .build()
        }
    }
}
