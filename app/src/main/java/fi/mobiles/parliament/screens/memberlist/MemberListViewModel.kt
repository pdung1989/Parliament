package fi.mobiles.parliament.screens.memberlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.mobiles.parliament.network.MembersApi
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [MemberListFragment].
 */

class MemberListViewModel: ViewModel() {
    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMembersProperties() on init so we can display status immediately.
     */
    init {
        getParliamentInfo()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun getParliamentInfo() {
        viewModelScope.launch {
            try {
                val listResult = MembersApi.retrofitService.getProperties().members
                _response.value = "Success: ${listResult.size} Members properties retrieved"
                Log.i("Numbers of members: ", "${listResult.size}")
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}