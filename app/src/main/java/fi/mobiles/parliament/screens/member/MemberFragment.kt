package fi.mobiles.parliament.screens.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentMemberBinding

//create a Fragment for displaying info of a member
class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding
    private lateinit var memberViewModel: MemberViewModel
    private val args: MemberFragmentArgs by navArgs()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var personNumber = args.personNumber

        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member, container, false)

        //val viewModelFactory = MemberViewModelFactory(requireContext())
        //initialize ViewModel
        memberViewModel = ViewModelProvider(this).get(MemberViewModel::class.java)

        // Specify the fragment view as the lifecycle owner of the binding.
        binding.lifecycleOwner = this

        //get member detail info
        memberViewModel.getMember(personNumber)
//        if (memberDetail != null) {
//            memberViewModel.memberInfo(memberDetail)
//        }
        //observe if member object has changed
        memberViewModel.member.observe(viewLifecycleOwner, Observer {
            member -> memberViewModel.member
        })

        return binding.root
    }
}