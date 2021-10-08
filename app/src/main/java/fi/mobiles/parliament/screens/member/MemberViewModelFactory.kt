package fi.mobiles.parliament.screens.member

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.mobiles.parliament.data.MemberDao

class MemberViewModelFactory(private val personNumber: Int): ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MemberViewModel::class.java)) {
                return MemberViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}