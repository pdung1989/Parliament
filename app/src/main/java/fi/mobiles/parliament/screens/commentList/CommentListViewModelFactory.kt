package fi.mobiles.parliament.screens.commentList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.mobiles.parliament.screens.member.MemberViewModel

class CommentListViewModelFactory: ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentListViewModel::class.java)) {
            return CommentListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}