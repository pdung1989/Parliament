package fi.mobiles.parliament.screens.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Name: DUNG TRAN (2012224)
 * Date: 1.10.2021
 * ViewModel Factory for creating Member View Model
 */

class MemberViewModelFactory(private val personNumber: Int): ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MemberViewModel()::class.java)) {
                return MemberViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}