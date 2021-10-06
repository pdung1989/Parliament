package fi.mobiles.parliament.screens.memberlist

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.MemberDao
import fi.mobiles.parliament.data.MemberDatabase
import fi.mobiles.parliament.network.MembersApi
import kotlinx.coroutines.launch


/**
 * The [ViewModel] that is attached to the [MemberListFragment].
 */
class MemberListViewModel(context: Context): ViewModel() {
    var database: MemberDao

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<List<Member>>()
    val response: LiveData<List<Member>>
        get() = _response

    //get list of members from database
    private lateinit var _allMembers: LiveData<List<Member>>
    val allMembers: LiveData<List<Member>>
        get() = _allMembers

    // navigate to member fragment
    private val _navigateToMember = MutableLiveData<Int?>()
    val navigateToMember
        get() = _navigateToMember

    init {
        database = MemberDatabase.getInstance().memberDao
        getParliamentInfo()
    }

    // Sets the value of the response LiveData to the Members API status or the successful
    // number of  Member properties retrieved.
    fun getParliamentInfo() {
        viewModelScope.launch {
            try {
                val fetchedData = MembersApi.retrofitService.getProperties()
                _response.value = fetchedData

                //insert data to Room
                if(fetchedData.isNotEmpty()) {
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