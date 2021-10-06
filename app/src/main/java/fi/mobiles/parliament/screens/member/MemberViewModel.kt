package fi.mobiles.parliament.screens.member

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.mobiles.parliament.R
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.MemberDao
import fi.mobiles.parliament.data.MemberDatabase
import kotlinx.coroutines.launch
import java.util.*

/**
 * ViewModel for MemberFragment.
 */
class MemberViewModel(context: Context): ViewModel() {
    private val database: MemberDao
    private lateinit var _member: LiveData<Member>
    val member: LiveData<Member>
        get() = _member

    lateinit var name: String
    lateinit var party: String
    lateinit var status: String
    lateinit var constituency: String
    lateinit var age: String
    var imageDrawable: Int = 0


    init {
        database = MemberDatabase.getInstance(context).memberDao
    }

   fun getMember(id: Int): Member? {
       var member = _member.value
        viewModelScope.launch {
            _member = database.getMember(id)
        }
       return member
    }

//    //private val parliamentData = ParliamentMembersData
//    private var members: List<MemberOfParliament> = ParliamentMembersData.members
//    var randomIndex: Int = 0
//    var name = ""
//    var age = ""
//    var party = ""
//    var status = ""
//    var constituency = ""
//    var imageDrawable: Int = 0
//
//    init {
//        Log.i("MemberViewModel", "MemberViewModel created!")
//    }
//
//    //get random index
//   fun getRandomIndex(members:List<MemberOfParliament>): Int {
//        val listOfIndex = members.withIndex().map { it.index }
//        randomIndex = listOfIndex.random()
//        return randomIndex
//    }
//
    //member Info by index
    fun memberInfo(member: Member) {
        party = member.party
        imageDrawable = when(party) {
            "sd" -> R.drawable.sd
            "ps" -> R.drawable.ps
            "kd" -> R.drawable.kd
            "kesk" -> R.drawable.kesk
            "kok" -> R.drawable.kok
            "vihr" -> R.drawable.vihr
            "liik" -> R.drawable.liik
            "vas" -> R.drawable.vas
            "r" -> R.drawable.r
            else -> R.drawable.ic_launcher_foreground
        }
        //binding.imageParty.setImageResource(drawableResource)
        //check if member is minister
        status = if(member.minister) "Minister" else "Member of Parliament"

        name = member.first + " " + member.last
        age = ((Calendar.getInstance().get(Calendar.YEAR)) - member.bornYear.toInt()).toString() +
                " " + "years-old"
        constituency = member.constituency
    }
//    //First member with index 0 in the List
//    fun getFirstMemberInfo() {
//        memberInfo(0)
//    }
//
//    //get random member info
//    fun getRandomMemberInfo () {
//        val index = getRandomIndex(members)
//        memberInfo(index)
//    }
}