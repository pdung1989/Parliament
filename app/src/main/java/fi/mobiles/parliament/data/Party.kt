package fi.mobiles.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Name: DUNG TRAN (2012224)
 * Date: 6.10.2021
 * Create Party table
 */
@Entity(tableName = "party_table")
data class Party(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "party")
    val party: String
)