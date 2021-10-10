package fi.mobiles.parliament.data

import androidx.lifecycle.LiveData

/**
 * Name: DUNG TRAN (2012224)
 * Date: 28.9.2021
 * Create Repository that contains database which needs for the App
 */

class MemberRepository(private val memberDao: MemberDao) {

    fun getAll(): LiveData<List<Member>> = memberDao.getAll()

    suspend fun insert(member: Member) = memberDao.insert(member)
    fun getMember(personNumber: Int): LiveData<Member> = memberDao.getMember(personNumber)

    suspend fun insertRating(rating: Rating) = memberDao.insertRating(rating)
    suspend fun insertComment(comment: Comment) = memberDao.insertComment(comment)

    fun getAllRatings(personNumber: Int): LiveData<List<Double>> = memberDao.getAllRatings(personNumber)
    fun getAllComments(personNumber: Int): LiveData<List<Comment>> = memberDao.getAllComments(personNumber)
}