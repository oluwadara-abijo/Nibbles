package com.dara.nibbles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_order.view.*

class OrderAdapter(
    var orders: List<Order>, val clickListener: ItemClickListener
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val imageView = itemView.img_nibble_image
        private val nameTextView = itemView.tv_nibble_name
        private val quantityTextView = itemView.tv_quantity
        private val amountTextView = itemView.tv_nibble_amount
        private val addButton = itemView.img_add
        private val removeButton = itemView.img_remove

        fun bind(order: Order) {
            imageView.setImageResource(order.nibble.image)
            nameTextView.text = order.nibble.name
            quantityTextView.text = order.quantity.toString()
            val amount = order.quantity * order.nibble.amount
            amountTextView.text = "$ ${amount.toBigDecimal().toPlainString()}"

            addButton.setOnClickListener(this)
            removeButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.img_add -> clickListener.onItemClick(orders[adapterPosition], true)
                R.id.img_remove -> clickListener.onItemClick(orders[adapterPosition], false)
                else -> return
            }

        }
    }

    interface ItemClickListener {
        fun onItemClick(order: Order, isAdded: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    internal fun setOrders(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }
}