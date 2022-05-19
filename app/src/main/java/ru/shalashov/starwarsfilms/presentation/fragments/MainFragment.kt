package ru.shalashov.starwarsfilms.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.shalashov.starwarsfilms.databinding.FragmentMainBinding
import ru.shalashov.starwarsfilms.domain.entities.Results

@AndroidEntryPoint
class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(film: Results)
    }

    companion object {
        fun newInstance() =
            MainFragment()
    }
}