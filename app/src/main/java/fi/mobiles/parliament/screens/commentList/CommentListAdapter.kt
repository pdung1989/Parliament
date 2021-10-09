package fi.mobiles.parliament.screens.commentList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fi.mobiles.parliament.R
import fi.mobiles.parliament.data.Comment
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.databinding.ListItemCommentBinding
import fi.mobiles.parliament.databinding.ListItemMemberBinding

/**
 * Name: DUNG TRAN (2012224)
 * Date: 9.10.2021
 * Adapter for CommentList RecyclerView
 */
class CommentListAdapter: ListAdapter<Comment, CommentListAdapter.ViewHolder>(CommentListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }

    // Call when the RecyclerView need a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemCommentBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.commentData = comment
            binding.commentItem.text = comment.comment
            //binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCommentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CommentListDiffCallback: DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}
