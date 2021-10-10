package fi.mobiles.parliament.screens.commentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fi.mobiles.parliament.data.*

/**
 * Name: DUNG TRAN (2012224)
 * Date: 9.10.2021
 * View Model for Comment List Fragment
 */

class CommentListViewModel : ViewModel() {
    //private val database: MemberDao
    private val database: MemberRepository

    private lateinit var _memberDetail: LiveData<Member>
    val memberDetail: LiveData<Member>
        get() = _memberDetail

    private lateinit var _memberComments: LiveData<List<Comment>>
    val memberComments: LiveData<List<Comment>>
        get() = _memberComments

    init {
        val memberDao = MemberDatabase.getInstance().memberDao
        database = MemberRepository(memberDao)
    }

    fun getMemberComments(personNumber: Int) {
        _memberComments = database.getAllComments(personNumber)
    }

    fun getMember(personNumber: Int) {
        _memberDetail = database.getMember(personNumber)
    }

}