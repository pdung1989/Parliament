package fi.mobiles.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_table")
    data class Member(
       @PrimaryKey(autoGenerate = true)
       val id: Long,

       @ColumnInfo(name = "last")
       val last: String,

       @ColumnInfo(name = "first")
       val first: String,

       @ColumnInfo(name = "party")
       val party: String,

       @ColumnInfo(name = "minister")
       val minister: Boolean,

       @ColumnInfo(name = "born_year")
       val bornYear: String,

       @ColumnInfo(name = "constituency")
       val constituency: String
 )
