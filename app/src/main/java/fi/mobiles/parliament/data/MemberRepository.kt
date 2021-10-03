//package fi.mobiles.parliament.data
//
//import androidx.lifecycle.LiveData
//
//class MemberRepository(private val memberDao: MemberDao) {
//
//    val readAllData: LiveData<List<Member>> = memberDao.getAll()
//
//    suspend fun insert(member: Member) {
//        memberDao.insert(member)
//    }
//
//    fun getALlParties(): List<String> {
//        return memberDao.getAllParties()
//    }
//
//    suspend fun getMembersByParty(party: String): List<Member> {
//        return memberDao.getMembersByParty(party)
//    }
//
//    suspend fun getMember(key: Long): Member? {
//        return memberDao.getMember(key)
//    }
//}