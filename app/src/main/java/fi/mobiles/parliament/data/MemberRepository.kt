package fi.mobiles.parliament.data

import androidx.lifecycle.LiveData

class MemberRepository(private val memberDao: MemberDao) {

    val readAllData: LiveData<List<Member>> = memberDao.getAll()

    suspend fun insert(member: Member) {
        memberDao.insert(member)
    }

    suspend fun update(member: Member) {
        memberDao.update(member)
    }

    fun getALlParties() {
        memberDao.getAllParties()
    }

    suspend fun getMembersByParty(party: String) {
        memberDao.getMembersByParty(party)
    }

    suspend fun getMember(key: Long) {
        memberDao.getMember(key)
    }
}