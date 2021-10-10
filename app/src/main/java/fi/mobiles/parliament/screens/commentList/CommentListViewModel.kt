package fi.mobiles.parliament.screens.commentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fi.mobiles.parliament.data.Comment
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.MemberDao
import fi.mobiles.parliament.data.MemberDatabase

/**
 * Name: DUNG TRAN (2012224)
 * Date: 9.10.2021
 * View Model for Comment List Fragment
 */

class CommentListViewModel : ViewModel() {
    private val database: MemberDao

    private lateinit var _memberDetail: LiveData<Member>
    val memberDetail: LiveData<Member>
        get() = _memberDetail

    private lateinit var _memberComments: LiveData<List<Comment>>
    val memberComments: LiveData<List<Comment>>
        get() = _memberComments

    init {
        database = MemberDatabase.getInstance().memberDao
    }

    fun getMemberComments(personNumber: Int) {
        _memberComments = database.getAllComments(personNumber)
    }

    fun getMember(personNumber: Int) {
        _memberDetail = database.getMember(personNumber)
    }

}