package fi.mobiles.parliament.network

data class ParliamentInfo (val members: List<MembersProperty>)

data class MembersProperty(
    val personNumber: String,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val twitter: String?,
    val bornYear: Int,
    val constituency: String
    )

