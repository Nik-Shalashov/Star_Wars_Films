package ru.shalashov.starwarsfilms.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.shalashov.starwarsfilms.R
import ru.shalashov.starwarsfilms.databinding.FragmentMainBinding
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms
import ru.shalashov.starwarsfilms.presentation.adapters.MainAdapter
import ru.shalashov.starwarsfilms.presentation.appState.AppState
import ru.shalashov.starwarsfilms.presentation.viewmodels.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFilmsList.adapter = adapter
        viewModel.filmsListLiveData.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getFilmsList()
        adapter.onItemViewClickListener = {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(it))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun renderData(appState: AppState<PopularFilms>) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val filmsList = appState.content.results
                adapter.setFilm(filmsList)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.fragmentFilmsList.showSnackBar(appState.error!!)
            }
        }
    }

    private fun View.showSnackBar(
        text: String,
        length: Int = Snackbar.LENGTH_LONG
    ) {
        Snackbar.make(this, text, length).show()
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    companion object {
        fun newInstance() =
            MainFragment()
    }
}