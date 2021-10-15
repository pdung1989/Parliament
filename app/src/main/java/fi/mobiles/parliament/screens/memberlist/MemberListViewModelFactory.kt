package fi.mobiles.parliament.screens.memberlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Name: DUNG TRAN (2012224)
 * Date: 1.10.2021
 * The ViewModel Factory for creating Member List ViewModel
 */

class MemberListViewModelFactory: ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MemberListViewModel::class.java)) {
                return MemberListViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}