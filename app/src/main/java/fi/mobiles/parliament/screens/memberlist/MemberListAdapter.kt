package fi.mobiles.parliament.screens.memberlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.mobiles.parliament.R
import fi.mobiles.parliament.data.Member



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
//        val res = holder.itemView.context.resources
        holder.name.text = item.first + " " + item.last
        holder.constituency.text = item.constituency

        val imageDrawable = when(item.party) {
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
        holder.partyImage.setImageResource(imageDrawable)
    }

    // Call when the RecyclerView need a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.list_item_member, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name)
        val constituency: TextView = itemView.findViewById(R.id.constituency)
        val partyImage: ImageView = itemView.findViewById(R.id.party_image)
    }

}