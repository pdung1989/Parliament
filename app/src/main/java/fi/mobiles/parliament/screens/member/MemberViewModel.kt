package fi.mobiles.parliament.screens.member

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.MemberDatabase
import fi.mobiles.parliament.data.MemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemberViewModel(application: Application, var repository: MemberRepository): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Member>>

    init {
        val memberDao = MemberDatabase.getInstance(application).memberDao
        repository = MemberRepository(memberDao)
        readAllData = repository.readAllData
    }

    fun insert(member: Member) {
        viewModelScope.launch {
            repository.insert(member)
        }
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
//    //member Info by index
//    fun memberInfo(index: Int) {
//        party = members[index].party
//        imageDrawable = when(party) {
//            "sd" -> R.drawable.sd
//            "ps" -> R.drawable.ps
//            "kd" -> R.drawable.kd
//            "kesk" -> R.drawable.kesk
//            "kok" -> R.drawable.kok
//            "vihr" -> R.drawable.vihr
//            "liik" -> R.drawable.liik
//            "vas" -> R.drawable.vas
//            "r" -> R.drawable.r
//            else -> R.drawable.ic_launcher_foreground
//        }
//        //binding.imageParty.setImageResource(drawableResource)
//        //check if member is minister
//        status = if(members[index].minister) "Minister" else "Member of Parliament"
//
//        name = members[index].first + " " + members[index].last
//        age = ((Calendar.getInstance().get(Calendar.YEAR)) -
//                members[index].bornYear).toString() + " " + "years-old"
//        constituency = members[index].constituency
//    }
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