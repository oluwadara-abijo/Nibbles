package com.dara.nibbles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_nibble.view.*
import java.math.RoundingMode

class NibbleAdapter(
    var nibbles: List<Nibble>, val clickListener: ItemClickListener
) : RecyclerView.Adapter<NibbleAdapter.NibbleViewHolder>() {

    inner class NibbleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val imageView = itemView.img_nibble_image
        private val nameTextView = itemView.tv_nibble_name
        private val descriptionTextView = itemView.tv_nibble_flavour
        private val amountTextView = itemView.tv_nibble_amount

        fun bind(nibble: Nibble) {
            imageView.setImageResource(nibble.image)
            nameTextView.text = nibble.name
            descriptionTextView.text = nibble.flavour
            amountTextView.text = "$ ${nibble.amount.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toPlainString()}"

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener.onItemClick(nibbles[adapterPosition])
        }

    }

    interface ItemClickListener {
        fun onItemClick(nibble: Nibble)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NibbleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_nibble, parent, false)
        return NibbleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nibbles.size
    }

    override fun onBindViewHolder(holder: NibbleViewHolder, position: Int) {
        val nibble = nibbles[position]
        holder.bind(nibble)
    }

    internal fun setNibbles(nibbles: List<Nibble>) {
        this.nibbles = nibbles
        notifyDataSetChanged()
    }

}