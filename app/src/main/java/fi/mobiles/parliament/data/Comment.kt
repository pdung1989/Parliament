package fi.mobiles.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Name: DUNG TRAN (2012224)
 * Date: 6.10.2021
 * Create comment table which includes comments of users for each parliament member
 */
@Entity(tableName = "comment_table")
data class Comment (
    @PrimaryKey
    val personNumber: Int,

    @ColumnInfo(name = "comment")
    val comment: String
)
