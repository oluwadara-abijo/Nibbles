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
class HomeFragment : Fragment(R.layout.fragment_home), CategoryAdapter.ItemClickListener,
    NibbleAdapter.ItemClickListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categories: List<Category>
    private lateinit var allNibbles: List<Nibble>
    private lateinit var nibbleAdapter: NibbleAdapter

    private lateinit var selectedCategory: Category

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedCategory = Category.BURGERS

        categories = listOf(Category.BURGERS, Category.NOODLES, Category.ROLLS, Category.DRINKS)
        categoryAdapter = CategoryAdapter(categories, this)
        setupCategoriesRecyclerView()

        allNibbles = listOf(
            Nibble(
                "Chicken Burger", R.drawable.ic_chicken_burger, "Flavoroso",
                "$ 12.00", Category.BURGERS
            ),
            Nibble(
                "Salmon Burgers", R.drawable.ic_salmon_burger, "Salty squid",
                "$ 14.00", Category.BURGERS
            ),
            Nibble(
                "Beef Cheese Burger", R.drawable.cheese_burger, "Mosala",
                "$ 18.00", Category.BURGERS
            ),
            Nibble(
                "Vegan Burger", R.drawable.ic_vegan_burger, "Vegan corners",
                "$ 10.00", Category.BURGERS
            ),
            Nibble(
                "Chinese noodles", R.drawable.ic_chow_mien, "Chow mien",
                "$ 30.00", Category.NOODLES
            ),
            Nibble(
                "Singapore noodles", R.drawable.ic_singapore_noodles, "Gluten free",
                "$ 20.00", Category.NOODLES
            ),
            Nibble(
                "Beef Roll", R.drawable.ic_beef_roll, "Beefy",
                "$ 5.00", Category.ROLLS
            ),
            Nibble(
                "Bread Roll", R.drawable.ic_bread_roll, "Spiral",
                "$ 8.00", Category.ROLLS
            ),
            Nibble(
                "Hot dog", R.drawable.ic_hot_dog, "With Ketchup",
                "$ 6.00", Category.ROLLS
            ),
            Nibble(
                "Pink Vodka", R.drawable.ic_pink_vodka, "Lemonade",
                "$ 40.00", Category.DRINKS
            ),
            Nibble(
                "Chapman", R.drawable.ic_chapman, "Cocktail",
                "$ 15.00", Category.DRINKS
            )
        )
        nibbleAdapter = NibbleAdapter(allNibbles.filter { it.category == selectedCategory }, this)
        setupNibblesRecyclerView()
    }

    private fun setupCategoriesRecyclerView() {
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        linearLayoutManager.isSmoothScrollbarEnabled = true
        rv_categories.apply {
            adapter = categoryAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun setupNibblesRecyclerView() {
        nibbleAdapter.setNibbles(allNibbles.filter { it.category == selectedCategory })
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        linearLayoutManager.isSmoothScrollbarEnabled = true
        rv_nibbles.apply {
            adapter = nibbleAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun onItemClick(category: Category) {
        selectedCategory = category
        setupNibblesRecyclerView()
    }

    override fun onItemClick(nibble: Nibble) {
        Toast.makeText(requireContext(), nibble.name, Toast.LENGTH_SHORT).show()
    }
}