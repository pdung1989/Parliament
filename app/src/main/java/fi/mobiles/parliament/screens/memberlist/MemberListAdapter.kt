package fi.mobiles.parliament.screens.memberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fi.mobiles.parliament.R
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.databinding.ListItemMemberBinding


class MemberListAdapter(private val clickListener: MemberListener): ListAdapter<Member, MemberListAdapter.ViewHolder>(MemberListDiffCallback()) {

//    var data = listOf<Member>()
//        @SuppressLint("NotifyDataSetChanged")
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
    //RecyclerView needs to know how many items the adapter has for it to display
    //override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    // Call when the RecyclerView need a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemMemberBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(member: Member, clickListener: MemberListener) {
            binding.clickListener = clickListener
            binding.member = member
            binding.name.text = member.first + " " + member.last
            binding.constituency.text = member.constituency

            val imageDrawable = when (member.party) {
                "sd" -> R.drawable.sd
                "ps" -> R.drawable.ps
                "kd" -> R.drawable.kd
                "kesk" -> R.drawable.kesk
                "kok" -> R.drawable.kok
                "vihr" -> R.drawable.vihr
                "liik" -> R.drawable.liik
                "vas" -> R.drawable.vas
                "r" -> R.drawable.r
                else -> R.drawable.ic_launcher_foreground
            }
            binding.partyImage.setImageResource(imageDrawable)
        }

        companion object {
             fun from(parent: ViewGroup): ViewHolder {
                 val layoutInflater = LayoutInflater.from(parent.context)
                 val binding = ListItemMemberBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class MemberListDiffCallback: DiffUtil.ItemCallback<Member>() {
    override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.personNumber == newItem.personNumber
    }

    override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem == newItem
    }
}
class MemberListener(val clickListener: (personNumber: Int) -> Unit) {
    fun onClick(member: Member) = clickListener(member.personNumber)
}