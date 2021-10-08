package fi.mobiles.parliament.screens.partylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentPartyListBinding

class PartyListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPartyListBinding>(inflater,
            R.layout.fragment_party_list, container, false)

//        binding.memberListButton.setOnClickListener{
//            findNavController().navigate(R.id.action_partyListFragment_to_memberListFragment)
//        }
        return binding.root
    }
}