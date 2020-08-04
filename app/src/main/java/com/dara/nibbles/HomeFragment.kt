package com.dara.nibbles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home), CategoryAdapter.ItemClickListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categories: List<Category>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories = listOf(
            Category("Burgers", R.drawable.ic_burger),
            Category("Noodles", R.drawable.ic_noodles),
            Category("Rolls", R.drawable.ic_roll),
            Category("Drinks", R.drawable.ic_drink)
        )
        categoryAdapter = CategoryAdapter(categories, this)
        setupCategoriesRecyclerView()
    }

    private fun setupCategoriesRecyclerView() {
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        linearLayoutManager.isSmoothScrollbarEnabled = true
        rv_categories.apply {
            adapter = categoryAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun onItemClick(category: Category) {
        Toast.makeText(requireContext(), category.title, Toast.LENGTH_SHORT).show()
    }
}