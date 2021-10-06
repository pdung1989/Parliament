package fi.mobiles.parliament.screens.memberlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentMemberListBinding


class MemberListFragment : Fragment() {
    lateinit var binding: FragmentMemberListBinding
    lateinit var memberListViewModel: MemberListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false )

        //create an object to access data
        val viewModelFactory = MemberListViewModelFactory(requireContext())

        //initialize ViewModel
        memberListViewModel = ViewModelProvider(this, viewModelFactory).get(MemberListViewModel::class.java)

        memberListViewModel.getAllMembers()
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = this

        // set Adapter for RecyclerView
        val adapter = MemberListAdapter(MemberListener { personNumber ->
            Toast.makeText(context, "${personNumber}", Toast.LENGTH_LONG).show()
            memberListViewModel.onMemberClicked(personNumber)
        })

        binding.memberList.adapter = adapter

        memberListViewModel.allMembers.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
                adapter.submitList(it)
            })

        //navigate to Member Fragment
        memberListViewModel.navigateToMember.observe(viewLifecycleOwner, Observer { personNumber ->
            personNumber?.let {
                this.findNavController().navigate(
                    MemberListFragmentDirections
                        .actionMemberListFragmentToMemberFragment(personNumber)
                )
                memberListViewModel.onMemberNavigated()
            }
        })

        return binding.root
    }
}