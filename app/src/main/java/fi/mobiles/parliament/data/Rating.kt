package fi.mobiles.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create Rating table which includes ratings got from users
 */
@Entity(tableName = "rating_table")
data class Rating(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "personNumber")
    val personNumber: Int,

    @ColumnInfo(name = "rating")
    val rating: Double
)