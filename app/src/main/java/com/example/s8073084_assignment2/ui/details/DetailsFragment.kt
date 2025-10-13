package com.example.s8073084_assignment2.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.s8073084_assignment2.R
import com.example.s8073084_assignment2.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        val entity = args.entity

        // Set the title on the CollapsingToolbarLayout
        binding.collapsingToolbar.title = entity.title

        // Set up the toolbar for navigation
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        // Load the book image
        Glide.with(this)
            .load(R.raw.book)
            .into(binding.headerImage)

        // Populate the rest of the views
        binding.authorText.text = entity.author
        binding.genreText.text = entity.genre
        binding.yearText.text = entity.publicationYear.toString()
        binding.descriptionText.text = entity.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}