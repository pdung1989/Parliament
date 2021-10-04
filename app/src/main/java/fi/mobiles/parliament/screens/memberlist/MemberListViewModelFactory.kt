package fi.mobiles.parliament.screens.memberlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.mobiles.parliament.data.MemberDao
import fi.mobiles.parliament.screens.member.MemberViewModel

class MemberListViewModelFactory (
    private val dataSource: MemberDao,
    private val application: Application): ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MemberViewModel::class.java)) {
                return MemberViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}