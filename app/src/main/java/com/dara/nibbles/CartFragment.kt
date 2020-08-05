package com.dara.nibbles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment(R.layout.fragment_cart), OrderAdapter.ItemClickListener {

    private lateinit var orders: List<Order>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var orderAdapter: OrderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val order1 = Order(
            Nibble(
                "Chicken Burger",
                R.drawable.ic_chicken_burger,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Flavoroso",
                12.00,
                Category.BURGERS
            ), 1
        )

        val order2 = Order(
            Nibble(
                "Singapore noodles",
                R.drawable.ic_singapore_noodles,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Gluten free",
                20.00,
                Category.NOODLES
            ), 3
        )

        val order3 = Order(
            Nibble(
                "Hot dog",
                R.drawable.ic_hot_dog,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "With Ketchup",
                6.00,
                Category.ROLLS
            ), 1
        )

        val order4 = Order(
            Nibble(
                "Chapman",
                R.drawable.ic_chapman,
                "Food consisting of one or more cooked patties of ground meat, usually beef, placed inside a bread roll or bun.",
                "Cocktail",
                15.00,
                Category.DRINKS
            ), 2
        )

        orders = listOf(order1, order2, order3, order4)
        setupOrdersRecyclerView()

        getTotal()

        val cartSize = orders.size.toString()
        tv_items_selected.text = "$cartSize items selected"
        tv_subtotal.text = "Subtotal ($cartSize items)"

        // Set click listener on back button
        img_back.setOnClickListener { findNavController().navigateUp() }
    }

    private fun setupOrdersRecyclerView() {
        orderAdapter = OrderAdapter(orders, this)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        linearLayoutManager.isSmoothScrollbarEnabled = true
        rv_orders.apply {
            adapter = orderAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun getTotal () {
        var total = 0.00
        for (order in orders) {
            val amount = (order.nibble.amount * order.quantity)
            total += amount
        }
        val totalString = total.toBigDecimal().toPlainString()
        tv_subtotal_amount.text = "$ $totalString"
        tv_total_amount.text = "$ $totalString"
    }

    override fun onItemClick(order: Order, isAdded: Boolean) {
        when (isAdded) {
            true -> {
                if (order.quantity > 0) order.quantity++
                orderAdapter.setOrders(orders)
                getTotal()
            }
            false -> {
                if (order.quantity > 1) order.quantity--
                orderAdapter.setOrders(orders)
                getTotal()
            }
        }
    }

}