package fi.mobiles.parliament.screens.member

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import fi.mobiles.parliament.MemberOfParliament
import fi.mobiles.parliament.ParliamentMembersData
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentMemberBinding
import java.util.*

//create a Fragment for displaying info of a member
class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding
    private val parliamentData = ParliamentMembersData
    private val members: List<MemberOfParliament> = ParliamentMembersData.members
    var randomIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater,
           R.layout.fragment_member, container, false)

        //Display member randomly by clicking Random button
        displayMember(randomIndex)
        binding.randomButton.setOnClickListener{
            getRandomIndex(members)
            displayMember(randomIndex)
        }
        //back to Party List screen
        binding.backToPartyButton.setOnClickListener{
            findNavController().navigate(R.id.action_memberFragment_to_partyListFragment)
        }
        return binding.root
    }
    //display info of a member on screen
    @SuppressLint("SetTextI18n")
    private fun displayMember(index: Int) {
        //set image for the party
        val drawableResource = when(members[index].party) {
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
        binding.imageParty.setImageResource(drawableResource)
        //check if member is minister
        binding.status.text = if(members[index].minister) "Minister" else "Member of Parliament"

        binding.firstname.text = members[index].first + " " + members[index].last
        binding.age.text = ((Calendar.getInstance().get(Calendar.YEAR)) -
                members[index].bornYear).toString() + " " + "years-old"
        binding.constituency.text = members[index].constituency
    }
    //get random index
    private fun getRandomIndex(members:List<MemberOfParliament>) {
        val listOfIndex = members.withIndex().map { it.index }
        randomIndex = listOfIndex.random()
    }
}