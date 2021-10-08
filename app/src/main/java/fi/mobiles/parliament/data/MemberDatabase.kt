package fi.mobiles.parliament.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.mobiles.parliament.MyApp

/**
 * A database that stores Member information.
 * And a global method to get access to the database.
 **/
//Create member database
@Database(entities = [Member::class, Rating::class, Comment::class], version = 2, exportSchema = false)
abstract class MemberDatabase : RoomDatabase() {
    abstract val memberDao: MemberDao

    companion object {
        @Volatile
        private var INSTANCE: MemberDatabase? = null
        fun getInstance(): MemberDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        MemberDatabase::class.java,
                        "member_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}