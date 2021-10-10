package fi.mobiles.parliament.screens.commentList

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
import fi.mobiles.parliament.databinding.FragmentCommentListBinding
import fi.mobiles.parliament.screens.member.MemberFragmentArgs
import fi.mobiles.parliament.screens.member.MemberViewModel
import fi.mobiles.parliament.screens.member.MemberViewModelFactory

/**
 * Name: DUNG TRAN (2012224)
 * Date: 9.10.2021
 * Fragment for displaying List of Comments
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
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_comment_list, container, false)

        // Specify the fragment view as the lifecycle owner of the binding.
        binding.lifecycleOwner = viewLifecycleOwner

        // Get argument for destination
        val personNumber = args.personNumber

        // Initialize ViewModel
        commentListViewModel = ViewModelProvider(this, CommentListViewModelFactory())
            .get(CommentListViewModel::class.java)

        // Get Member Detail then Get her/his name
        commentListViewModel.getMember(personNumber)
        commentListViewModel.memberDetail.observe(viewLifecycleOwner, Observer {
            member -> member?.let {
            binding.member = member
            }
        })

        // Get member comments
        commentListViewModel.getMemberComments(personNumber)

        // Set adapter for the view
        val adapter = CommentListAdapter()
        binding.commentList.adapter = adapter

        //Observe any time when the List<Comment> changes
        commentListViewModel.memberComments.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
                adapter.submitList(it)
            })

        return binding.root
    }
}