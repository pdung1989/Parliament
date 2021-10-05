package fi.mobiles.parliament.screens.memberlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fi.mobiles.parliament.R
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.databinding.ListItemMemberBinding


class MemberListAdapter: RecyclerView.Adapter<MemberListAdapter.ViewHolder>() {
    var data = listOf<Member>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    //RecyclerView needs to know how many items the adapter has for it to display
    override fun getItemCount() = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    // Call when the RecyclerView need a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemMemberBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(item: Member) {
            binding.name.text = item.first + " " + item.last
            binding.constituency.text = item.constituency

            val imageDrawable = when (item.party) {
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
                 val binding =
                     ListItemMemberBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}