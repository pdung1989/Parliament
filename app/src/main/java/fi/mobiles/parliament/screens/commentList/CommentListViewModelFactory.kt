package fi.mobiles.parliament.screens.commentList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Name: DUNG TRAN (2012224)
 * Date: 9.10.2021
 * ViewModel Factory for Comment List ViewModel
 */

class CommentListViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentListViewModel::class.java)) {
            return CommentListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}