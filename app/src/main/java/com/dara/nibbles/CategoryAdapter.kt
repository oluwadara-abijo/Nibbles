package com.dara.nibbles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_category.view.*

class CategoryAdapter(
    var categories: List<Category>, val clickListener: ItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val imageView = itemView.img_category_image
        private val textView = itemView.tv_category_title
        private val rootView = itemView.root_view

        fun bind(category: Category) {
            imageView.setImageResource(category.image)
            textView.text = category.title

            if (selectedPosition == -1) {
                deselectItem(rootView)
            } else {
                if (selectedPosition == adapterPosition) {
                    selectItem(rootView)
                } else {
                    deselectItem(rootView)
                }
            }

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectItem(rootView)

            if (selectedPosition != adapterPosition) {
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
            }

            clickListener.onItemClick(categories[adapterPosition])
        }

    }

    interface ItemClickListener {
        fun onItemClick(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    private fun deselectItem(rootView: ConstraintLayout) {
        rootView.setBackgroundResource(R.drawable.bg_white_rectangle)
    }

    private fun selectItem(rootView: ConstraintLayout) {
        rootView.setBackgroundResource(R.drawable.bg_yellow_rectangle)
    }
}