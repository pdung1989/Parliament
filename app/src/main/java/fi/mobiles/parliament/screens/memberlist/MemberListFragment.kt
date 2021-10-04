package fi.mobiles.parliament.screens.memberlist

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
import fi.mobiles.parliament.databinding.FragmentMemberListBinding
import fi.mobiles.parliament.screens.member.MemberViewModel
import fi.mobiles.parliament.screens.member.MemberViewModelFactory


class MemberListFragment : Fragment() {
    lateinit var binding: FragmentMemberListBinding
    lateinit var memberListViewModel: MemberListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false )

        // The requireNotNull Kotlin function throws an IllegalArgumentException if the value is null
        val application = requireNotNull(this.activity).application

        //create an instance from DAO
        val dataSource = MemberDatabase.getInstance(application).memberDao

        val viewModelFactory = MemberListViewModelFactory(dataSource, application)

        //initialize ViewModel
        memberListViewModel = ViewModelProvider(this, viewModelFactory).get(MemberListViewModel::class.java)

        //binding ViewModel
        binding.setLifecycleOwner(this)

        binding.memberListViewModel = memberListViewModel

        binding.detailMemberButton.setOnClickListener{
            findNavController().navigate(R.id.action_memberListFragment_to_memberFragment)
        }

        return binding.root
    }

}