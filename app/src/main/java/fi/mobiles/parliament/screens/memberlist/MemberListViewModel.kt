package fi.mobiles.parliament.screens.memberlist

import android.util.Log
import androidx.lifecycle.*
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.MemberDao
import fi.mobiles.parliament.data.MemberDatabase
import fi.mobiles.parliament.data.MemberRepository
import fi.mobiles.parliament.network.MembersApi
import kotlinx.coroutines.launch


/**
 * Name: DUNG TRAN (2012224)
 * Date: 1.10.2021
 * The ViewModel that is attached to the MemberListFragment.
 */
class MemberListViewModel : ViewModel() {

    private val database: MemberRepository
    //private val database: MemberDao

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<List<Member>>()
    val response: LiveData<List<Member>>
        get() = _response

    // Get list of members from database
    private lateinit var _allMembers: LiveData<List<Member>>
    val allMembers: LiveData<List<Member>>
        get() = _allMembers

    // Navigate to member fragment
    private val _navigateToMember = MutableLiveData<Int?>()
    val navigateToMember
        get() = _navigateToMember

    init {
        val memberDao = MemberDatabase.getInstance().memberDao
        database = MemberRepository(memberDao)
        getParliamentInfo()
    }

    // Fetch data from internet using retrofit and moshi libraries, then store data in Room
    fun getParliamentInfo() {
        viewModelScope.launch {
            try {
                val fetchedData = MembersApi.retrofitService.getProperties()
                _response.value = fetchedData

                //insert data to Room
                if (fetchedData.isNotEmpty()) {
                    fetchedData.forEach {
                        database.insert(it)
                    }
                }
            } catch (e: Exception) {
                _response.value = ArrayList()
                Log.i("error", e.message.toString())
            }
        }
    }

    fun getAllMembers() {
        _allMembers = database.getAll()
    }

    fun onMemberClicked(personNumber: Int) {
        _navigateToMember.value = personNumber
    }

    fun onMemberNavigated() {
        _navigateToMember.value = null
    }
}