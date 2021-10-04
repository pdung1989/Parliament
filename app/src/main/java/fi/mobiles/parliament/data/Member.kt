package fi.mobiles.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_table")
    data class Member(
       @PrimaryKey(autoGenerate = true)
       val id: Long,

       @ColumnInfo(name = "person_number")
       val personNumber: String,

       @ColumnInfo(name = "seat_number")
       val seatNumber: Int,

       @ColumnInfo(name = "last_name")
       val last: String,

       @ColumnInfo(name = "first_name")
       val first: String,

       @ColumnInfo(name = "party")
       val party: String,

       @ColumnInfo(name = "minister")
       val minister: Boolean,

       @ColumnInfo(name = "picture")
       val picture: String,

       @ColumnInfo(name = "twitter")
       val twitter: String?,

       @ColumnInfo(name = "born_year")
       val bornYear: String,

       @ColumnInfo(name = "constituency")
       val constituency: String
 )
