package fi.mobiles.parliament.screens.memberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.mobiles.parliament.R
import fi.mobiles.parliament.data.Member

var data =  listOf<Member>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
class MemberListAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    //RecyclerView needs to know how many items the adapter has for it to display
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]

        holder.textView.text = item.first
    }

    // Call when the RecyclerView need a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)

    }
}