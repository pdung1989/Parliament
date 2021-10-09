package fi.mobiles.parliament.screens.commentList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentCommentListBinding
import fi.mobiles.parliament.screens.member.MemberFragmentArgs
import fi.mobiles.parliament.screens.member.MemberViewModel
import fi.mobiles.parliament.screens.member.MemberViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [CommentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentListFragment : Fragment() {
    private lateinit var binding: FragmentCommentListBinding
    private lateinit var commentListViewModel: CommentListViewModel
    private val args: MemberFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment_list, container, false)
        //binding.lifecycleOwner = viewLifecycleOwner

        // get argument for destination
        val personNumber = args.personNumber

        // Initialize ViewModel
        commentListViewModel = ViewModelProvider(this, CommentListViewModelFactory()).get(CommentListViewModel::class.java)

        // get member comments
        commentListViewModel.getMemberComments(personNumber)

        val adapter = CommentListAdapter()
        binding.commentList.adapter = adapter

        //Observe any time when the List<Member> changes
        commentListViewModel.memberComments.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
            adapter.submitList(it)
        })

        return binding.root
    }
}