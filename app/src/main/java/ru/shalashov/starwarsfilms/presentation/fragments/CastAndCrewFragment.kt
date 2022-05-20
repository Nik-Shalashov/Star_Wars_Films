package ru.shalashov.starwarsfilms.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.shalashov.starwarsfilms.databinding.FragmentCastAndCrewBinding
import ru.shalashov.starwarsfilms.databinding.FragmentDetailsBinding
import ru.shalashov.starwarsfilms.presentation.adapters.CreditsAdapter
import ru.shalashov.starwarsfilms.presentation.viewmodels.DetailsViewModel

@AndroidEntryPoint
class CastAndCrewFragment: Fragment() {

    private var _binding: FragmentCastAndCrewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var adapter: CreditsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCastAndCrewBinding.inflate(inflater, container, false)
        return binding.root
    }
}