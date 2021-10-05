package fi.mobiles.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_table")
    data class Member(
       @PrimaryKey
       val personNumber: String,

       val seatNumber: Int,

       val last: String,

       val first: String,

       val party: String,

       val minister: Boolean,

       val picture: String,

       val twitter: String?,

       val bornYear: String,

       val constituency: String
 )
