package fi.mobiles.parliament.screens.memberlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MemberListViewModelFactory(): ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MemberListViewModel::class.java)) {
                return MemberListViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}