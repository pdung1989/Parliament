package fi.mobiles.parliament.screens.commentList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fi.mobiles.parliament.data.Comment
import fi.mobiles.parliament.databinding.ListItemCommentBinding

/**
 * Name: DUNG TRAN (2012224)
 * Date: 1.10.2021
 * Adapter for CommentList RecyclerView
 */
class CommentListAdapter :
    ListAdapter<Comment, CommentListAdapter.ViewHolder>(CommentListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }

    // Call when the RecyclerView need a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.commentData = comment
            binding.commentItem.text = comment.comment
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

// Use DiffCallBack to update the changes in the list of items
class CommentListDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}
