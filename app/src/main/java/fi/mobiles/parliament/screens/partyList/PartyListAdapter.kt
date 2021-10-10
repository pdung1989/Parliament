package fi.mobiles.parliament.screens.partyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fi.mobiles.parliament.R
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.Party
import fi.mobiles.parliament.databinding.ListItemPartyBinding

/**
 * Name: DUNG TRAN (2012224)
 * Date: 1.10.2021
 * Adapter for PartyList RecyclerView
 */
class PartyListAdapter(private val clickListener: PartyListener): ListAdapter<Party, PartyListAdapter.ViewHolder>(PartyListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    // Call when the RecyclerView need a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemPartyBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(party: Party, clickListener: PartyListener) {
            binding.partyData = party
            binding.clickListener = clickListener
            binding.executePendingBindings()

            val imageDrawable = when (party.party) {
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
                val binding = ListItemPartyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

// Use DiffCallBack to update the changes in the list of items
class PartyListDiffCallback: DiffUtil.ItemCallback<Party>() {
    override fun areItemsTheSame(oldItem: Party, newItem: Party): Boolean {
        return oldItem.party == newItem.party
    }

    override fun areContentsTheSame(oldItem: Party, newItem: Party): Boolean {
        return oldItem == newItem
    }
}
class PartyListener(val clickListener: (party: String) -> Unit) {
    fun onClick(party: String) = clickListener(party)
}