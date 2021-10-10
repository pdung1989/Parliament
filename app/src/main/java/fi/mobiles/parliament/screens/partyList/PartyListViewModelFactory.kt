package fi.mobiles.parliament.screens.partyList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.mobiles.parliament.screens.memberlist.MemberListViewModel

/**
 * Name: DUNG TRAN (2012224)
 * Date: 1.10.2021
 * The ViewModel Factory for creating Party List ViewModel
 */

class PartyListViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartyListViewModel::class.java)) {
            return PartyListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}