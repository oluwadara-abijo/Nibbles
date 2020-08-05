package com.dara.nibbles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
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
                "Chicken Burger",
                R.drawable.ic_chicken_burger,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Flavoroso",
                12.00,
                Category.BURGERS
            ),
            Nibble(
                "Salmon Burgers",
                R.drawable.ic_salmon_burger,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Salty squid",
                14.00,
                Category.BURGERS
            ),
            Nibble(
                "Beef Cheese Burger",
                R.drawable.cheese_burger,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Mosala",
                18.00,
                Category.BURGERS
            ),
            Nibble(
                "Vegan Burger",
                R.drawable.ic_vegan_burger,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Vegan corners",
                10.00,
                Category.BURGERS
            ),
            Nibble(
                "Chinese noodles",
                R.drawable.ic_chow_mien,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Chow mien",
                30.00,
                Category.NOODLES
            ),
            Nibble(
                "Singapore noodles",
                R.drawable.ic_singapore_noodles,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Gluten free",
                20.00,
                Category.NOODLES
            ),
            Nibble(
                "Beef Roll",
                R.drawable.ic_beef_roll,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Beefy",
                5.00,
                Category.ROLLS
            ),
            Nibble(
                "Bread Roll",
                R.drawable.ic_bread_roll,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Spiral",
                8.00,
                Category.ROLLS
            ),
            Nibble(
                "Hot dog",
                R.drawable.ic_hot_dog,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "With Ketchup",
                6.00,
                Category.ROLLS
            ),
            Nibble(
                "Pink Vodka",
                R.drawable.ic_pink_vodka,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Lemonade",
                40.00,
                Category.DRINKS
            ),
            Nibble(
                "Chapman",
                R.drawable.ic_chapman,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Cocktail",
                15.00,
                Category.DRINKS
            )
        )
        nibbleAdapter = NibbleAdapter(allNibbles.filter { it.category == selectedCategory }, this)
        setupNibblesRecyclerView()

        // Set click listener on cart button
        img_cart.setOnClickListener {
            val action = HomeFragmentDirections.actionGlobalCartFragment()
            findNavController().navigate(action)
        }
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
        val action = HomeFragmentDirections.actionHomeFragmentToNibbleDetailFragment(nibble)
        findNavController().navigate(action)
    }
}