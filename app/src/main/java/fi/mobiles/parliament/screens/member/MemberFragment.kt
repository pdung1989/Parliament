package fi.mobiles.parliament.screens.member

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fi.mobiles.parliament.MemberOfParliament
import fi.mobiles.parliament.ParliamentMembersData
import fi.mobiles.parliament.ParliamentMembersData.members
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentMemberBinding
import java.util.*

//create a Fragment for displaying info of a member
class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding

    private lateinit var viewModel: MemberViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(
           inflater,
           R.layout.fragment_member,
           container,
           false)

        Log.i("MemberFragment", "Called ViewModelProvider.get")
        //initialize ViewModel
        viewModel = ViewModelProvider(this).get(MemberViewModel::class.java)

        //Display member randomly by clicking Random button
        viewModel.getFirstMemberInfo()
        displayMember()
        binding.randomButton.setOnClickListener{
            viewModel.getRandomMemberInfo()
            displayMember()
        }
        //back to Party List screen
        binding.backToPartyButton.setOnClickListener{
            findNavController().navigate(R.id.action_memberFragment_to_partyListFragment)
        }
        return binding.root
    }
    //display info of a member on screen
    private fun displayMember() {
        binding.imageParty.setImageResource(viewModel.imageDrawable)
        //check if member is minister
        binding.status.text = viewModel.status

        binding.firstname.text = viewModel.name
        binding.age.text = viewModel.age
        binding.constituency.text = viewModel.constituency
    }
}