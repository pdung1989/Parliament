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
import fi.mobiles.parliament.data.MemberDatabase
import fi.mobiles.parliament.databinding.FragmentMemberBinding
import java.util.*

//create a Fragment for displaying info of a member
class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding

    private lateinit var memberViewModel: MemberViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(
           inflater,
           R.layout.fragment_member,
           container,
           false)

       // The requireNotNull Kotlin function throws an IllegalArgumentException if the value is null
        val application = requireNotNull(this.activity).application

        //create an instance from DAO
        val dataSource = MemberDatabase.getInstance(application).memberDao

        val viewModelFactory = MemberViewModelFactory(dataSource, application)

        Log.i("MemberFragment", "Called ViewModelProvider.get")

        //initialize ViewModel
        memberViewModel = ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.memberViewModel = memberViewModel

//        //Display member randomly by clicking Random button
//        viewModel.getFirstMemberInfo()
//        displayMember()
//        binding.randomButton.setOnClickListener{
//            viewModel.getRandomMemberInfo()
//            displayMember()
//        }
        //back to Party List screen
        binding.backToPartyButton.setOnClickListener{
            findNavController().navigate(R.id.action_memberFragment_to_partyListFragment)
        }

        return binding.root
    }
//    //display info of a member on screen
//    private fun displayMember() {
//        binding.imageParty.setImageResource(viewModel.imageDrawable)
//        binding.status.text = viewModel.status
//
//        binding.firstname.text = viewModel.name
//        binding.age.text = viewModel.age
//        binding.constituency.text = viewModel.constituency
//    }
}