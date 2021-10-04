package fi.mobiles.parliament.screens.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        //create an object to access data
        val dataSource = MemberDatabase.getInstance(application).memberDao

        val viewModelFactory = MemberViewModelFactory(dataSource, application)

        //initialize ViewModel
        memberViewModel = ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)

        // Specify the fragment view as the lifecycle owner of the binding.
        binding.setLifecycleOwner(this)

        //binding viewModel
        binding.memberViewModel = memberViewModel

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