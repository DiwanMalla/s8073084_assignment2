package com.example.s8073084_assignment2.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
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
        binding.titleText.text = entity.title
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