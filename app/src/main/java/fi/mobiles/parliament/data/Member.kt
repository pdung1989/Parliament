package fi.mobiles.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_table")
 data class Member(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "last")
    var last: String,

    @ColumnInfo(name = "first")
    var first: String,

    @ColumnInfo(name = "party")
    var party: String,

    @ColumnInfo(name = "minister")
    var minister: Boolean,

    @ColumnInfo(name = "born_year")
    var bornYear: String,

    @ColumnInfo(name = "constituency")
    var constituency: String
 )
