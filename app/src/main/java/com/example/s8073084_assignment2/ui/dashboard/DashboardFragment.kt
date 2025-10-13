package com.example.s8073084_assignment2.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.s8073084_assignment2.R
import com.example.s8073084_assignment2.databinding.FragmentDashboardBinding
import com.example.s8073084_assignment2.util.ThemeManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()
    private val args: DashboardFragmentArgs by navArgs()

    @Inject
    lateinit var themeManager: ThemeManager

    private lateinit var dashboardAdapter: DashboardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)

        setupRecyclerView()
        setupThemeToggle()

        viewModel.loadDashboard(args.keypass)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dashboardState.collectLatest { state ->
                binding.progressBar.isVisible = state is DashboardUiState.Loading

                if (state is DashboardUiState.Success) {
                    dashboardAdapter.submitList(state.entities)
                } else if (state is DashboardUiState.Error) {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        dashboardAdapter = DashboardAdapter {
            val action = DashboardFragmentDirections.actionDashboardFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.dashboardRecyclerview.adapter = dashboardAdapter
    }

    private fun setupThemeToggle() {
        lifecycleScope.launch {
            themeManager.isDarkMode.collectLatest {
                binding.themeSwitch.isChecked = it
            }
        }

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                themeManager.setDarkMode(isChecked)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}