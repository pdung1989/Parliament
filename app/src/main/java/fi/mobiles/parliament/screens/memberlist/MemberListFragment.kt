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

/**
 * Fragment for MemberList
 */
class MemberListFragment : Fragment() {
    lateinit var binding: FragmentMemberListBinding
    lateinit var memberListViewModel: MemberListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false )
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModelFactory = MemberListViewModelFactory()
        // Initialize ViewModel
        memberListViewModel = ViewModelProvider(this, viewModelFactory).get(MemberListViewModel::class.java)
        // Get List of members
        memberListViewModel.getAllMembers()

        // Set Adapter for RecyclerView
        val adapter = MemberListAdapter(MemberListener { personNumber ->
            Toast.makeText(context, "${personNumber}", Toast.LENGTH_LONG).show()
            memberListViewModel.onMemberClicked(personNumber)
        })
        binding.memberList.adapter = adapter

        // Observe any time when the List<Member> changes
        memberListViewModel.allMembers.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
                adapter.submitList(it)
            })

        // Navigate to Comment Fragment
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