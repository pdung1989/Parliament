package fi.mobiles.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Name: DUNG TRAN (2012224)
 * Date: 28.9.2021
 * Create Member table to display data of a member
 */
@Entity(tableName = "member_table")
    data class Member(
       @PrimaryKey
       val personNumber: Int,

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
