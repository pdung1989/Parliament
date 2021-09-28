package fi.mobiles.parliament.screens.member

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import fi.mobiles.parliament.MemberOfParliament
import fi.mobiles.parliament.ParliamentMembersData
import fi.mobiles.parliament.R
import java.util.*

class MemberViewModel: ViewModel() {
    //private val parliamentData = ParliamentMembersData
    private var members: List<MemberOfParliament> = ParliamentMembersData.members
    var randomIndex: Int = 0
    var name = ""
    var age = ""
    var party = ""
    var status = ""
    var constituency = ""
    var imageDrawable: Int = 0

    init {
        Log.i("MemberViewModel", "MemberViewModel created!")
    }

    //get random index
   fun getRandomIndex(members:List<MemberOfParliament>): Int {
        val listOfIndex = members.withIndex().map { it.index }
        randomIndex = listOfIndex.random()
        return randomIndex
    }

    //member Info by index
    fun memberInfo(index: Int) {
        party = members[index].party
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
        status = if(members[index].minister) "Minister" else "Member of Parliament"

        name = members[index].first + " " + members[index].last
        age = ((Calendar.getInstance().get(Calendar.YEAR)) -
                members[index].bornYear).toString() + " " + "years-old"
        constituency = members[index].constituency
    }
    //First member with index 0 in the List
    fun getFirstMemberInfo() {
        memberInfo(0)
    }

    //get random member info
    fun getRandomMemberInfo () {
        val index = getRandomIndex(members)
        memberInfo(index)
    }
}