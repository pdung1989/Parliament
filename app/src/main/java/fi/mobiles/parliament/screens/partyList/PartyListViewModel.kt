package fi.mobiles.parliament.screens.partyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fi.mobiles.parliament.data.MemberDatabase
import fi.mobiles.parliament.data.MemberRepository
import fi.mobiles.parliament.data.Party

class PartyListViewModel : ViewModel() {
    private val database: MemberRepository

    private lateinit var _allParties: LiveData<List<Party>>
    val allParties: LiveData<List<Party>>
        get() = _allParties

    init {
        val memberDao = MemberDatabase.getInstance().memberDao
        database = MemberRepository(memberDao)
    }

    fun getParties() {
        _allParties = database.getAllParties()
    }
}