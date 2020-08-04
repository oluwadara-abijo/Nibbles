package com.dara.nibbles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_nibble_detail.*

class NibbleDetailFragment : Fragment(R.layout.fragment_nibble_detail) {

    private val args: NibbleDetailFragmentArgs by navArgs()
    private lateinit var nibble: Nibble

    private var quantity: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nibble = args.nibble
        populateUI(nibble)

        img_back.setOnClickListener { findNavController().navigateUp() }

        tv_quantity.text = quantity.toString()

        img_add.setOnClickListener {
            if (quantity > 0) {
                quantity++
                tv_quantity.text = quantity.toString()
            }
        }

        img_remove.setOnClickListener {
            if (quantity > 1) {
                quantity--
                tv_quantity.text = quantity.toString()
            }
        }
    }

    private fun populateUI(nibble: Nibble) {
        img_nibble_image.setImageResource(nibble.image)
        tv_nibble_name.text = nibble.name
        tv_nibble_description.text = nibble.description
        tv_nibble_amount.text = nibble.amount
    }
}