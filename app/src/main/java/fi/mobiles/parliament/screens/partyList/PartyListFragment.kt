package fi.mobiles.parliament.screens.partyList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentPartyListBinding

/**
 * Name: DUNG TRAN (2012224)
 * Date: 10.10.2021
 * Fragment for PartyList
 */
class PartyListFragment : Fragment() {
    lateinit var binding: FragmentPartyListBinding
    lateinit var partyListViewModel: PartyListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModelFactory = PartyListViewModelFactory()
        // Initialize ViewModel
        partyListViewModel =
            ViewModelProvider(this, viewModelFactory).get(PartyListViewModel::class.java)
        // Get List of parties
        partyListViewModel.getParties()

        // Set Adapter for RecyclerView
        val adapter = PartyListAdapter(PartyListener { party ->
            partyListViewModel.onPartyClicked(party)
        })
        binding.partyList.adapter = adapter

        // Observe any time when the List<Party> changes
        partyListViewModel.allParties.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { partyList -> partyList?.let{
                adapter.submitList(partyList)
            }
            })

        // Navigate to Member List Fragment
        partyListViewModel.navigateToMemberList.observe(viewLifecycleOwner, Observer { party ->
            party?.let {
                this.findNavController().navigate(
                    PartyListFragmentDirections
                        .actionPartyListFragmentToMemberListFragment(party)
                )
                partyListViewModel.onPartyNavigated()
            }
        })

        return binding.root
    }
}