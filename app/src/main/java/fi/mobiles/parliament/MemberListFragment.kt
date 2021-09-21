package fi.mobiles.parliament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import fi.mobiles.parliament.databinding.FragmentMemberListBinding


class MemberListFragment : Fragment() {
    lateinit var binding: FragmentMemberListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false )

        binding.detailMemberButton.setOnClickListener{
            findNavController().navigate(R.id.action_memberListFragment_to_memberFragment)
        }
        return binding.root
    }

}