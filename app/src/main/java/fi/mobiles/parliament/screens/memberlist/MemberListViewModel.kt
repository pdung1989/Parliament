package fi.mobiles.parliament.screens.memberlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import fi.mobiles.parliament.data.MemberDao
import fi.mobiles.parliament.network.MembersApi
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [MemberListFragment].
 */
class MemberListViewModel(val database: MemberDao,
                           application: Application): AndroidViewModel(application) {
    // The internal MutableLiveData String that stores the most recent response
    val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    val memberList = database.getAll()

    init {
        getParliamentInfo()
    }

    // Sets the value of the response LiveData to the Members API status or the successful
    // number of  Member properties retrieved.
    private fun getParliamentInfo() {
        viewModelScope.launch {
            try {
                val listResult = MembersApi.retrofitService.getProperties()
                _response.value =
                    "Success: ${listResult.size} Member properties retrieved"
                    if(listResult.isNotEmpty()) {
                        listResult.map {database.insert(it)}
                    }
                Log.i("Numbers of members: ", "${listResult.size}")
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}