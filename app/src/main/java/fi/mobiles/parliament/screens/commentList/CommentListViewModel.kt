package fi.mobiles.parliament.screens.commentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fi.mobiles.parliament.data.Comment
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.MemberDao
import fi.mobiles.parliament.data.MemberDatabase

class CommentListViewModel(): ViewModel() {
    private val database: MemberDao

//     private lateinit var _personNumber: LiveData<Int>
//     val personNumber: LiveData<Int>
//          get() = _personNumber

     private lateinit var _memberComments: LiveData<List<Comment>>
     val memberComments: LiveData<List<Comment>>
          get() = _memberComments


     init {
         database = MemberDatabase.getInstance().memberDao
    }

     fun getMemberComments(personNumber: Int) {
          _memberComments = database.getAllComments(personNumber)
     }

}