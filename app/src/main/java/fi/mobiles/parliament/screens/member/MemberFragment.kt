package fi.mobiles.parliament.screens.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentMemberBinding

//create a Fragment for displaying info of a member
class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding
    private lateinit var memberViewModel: MemberViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member, container, false)

        val viewModelFactory = MemberViewModelFactory(requireContext())
        //initialize ViewModel
        memberViewModel = ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)

        // Specify the fragment view as the lifecycle owner of the binding.
        binding.lifecycleOwner = this

        //get member detail info
        val memberDetail = memberViewModel.getMember(id)
        if (memberDetail != null) {
            memberViewModel.memberInfo(memberDetail)
        }
        //observe if member object has changed
        memberViewModel.member.observe(viewLifecycleOwner, Observer {
            member -> memberViewModel.member
        })


//        //binding viewModel
//        binding.memberViewModel = memberViewModel

        //back to Party List screen
//        binding.backToPartyButton.setOnClickListener{
//            findNavController().navigate(R.id.action_memberFragment_to_partyListFragment)
//        }

        return binding.root
    }
}