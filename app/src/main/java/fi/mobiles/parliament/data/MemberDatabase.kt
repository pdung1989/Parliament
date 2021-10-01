package fi.mobiles.parliament.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * A database that stores Member information.
 * And a global method to get access to the database.
 **/
//Create member database
@Database(entities = [Member::class], version = 1, exportSchema = false)
abstract class MemberDatabase : RoomDatabase() {
    abstract val memberDao: MemberDao

    companion object {
        @Volatile
        private var INSTANCE: MemberDatabase? = null
        fun getInstance(context: Context): MemberDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
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