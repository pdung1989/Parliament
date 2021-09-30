package fi.mobiles.parliament.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_table")
 data class Member(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var last: String,
    var first: String,
    var party: String,
    var minister: Boolean,
    var bornYear: String,
    var constituency: String
 )
