package ru.shalashov.starwarsfilms.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.shalashov.starwarsfilms.R
import ru.shalashov.starwarsfilms.databinding.FragmentDetailsBinding
import ru.shalashov.starwarsfilms.databinding.FragmentMainBinding
import ru.shalashov.starwarsfilms.domain.entities.*
import ru.shalashov.starwarsfilms.presentation.adapters.CreditsAdapter
import ru.shalashov.starwarsfilms.presentation.appState.AppState
import ru.shalashov.starwarsfilms.presentation.viewmodels.DetailsViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var adapter: CreditsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailsLiveData.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.creditsLiveData.observe(viewLifecycleOwner) { renderDataForCredits(it) }
        val filmID = requireArguments().getParcelable<Results>(BUNDLE_EXTRA)!!.id
        viewModel.getDetails(filmID)
        viewModel.getCredits(filmID)
        binding.ibBackToFilms.setOnClickListener {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.container, MainFragment())
                    .commitAllowingStateLoss()
            }
        }
        binding.tvViewAll.setOnClickListener {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, CastAndCrewFragment())
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    }

    private fun renderData(appState: AppState<Details>) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val details = appState.content
                binding.ivPosterInDescription.load("https://image.tmdb.org/t/p/w500${details.poster_path}")
                binding.tvDurationInDescription.text = details.runtime.toString()
                binding.tvFilmNameInDescription.text = details.title
                binding.tvDescription.text = details.overview
                val genre = details.genres[1].name
                binding.tvGenreInDescription.text = genre
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.fragmentDetails.showSnackBar(appState.error!!)
            }
        }
    }

    private fun renderDataForCredits(appState: AppState<Credits>) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val credits = appState.content
                adapter = CreditsAdapter(credits)
                binding.rvCreditsList.adapter = adapter
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.fragmentDetails.showSnackBar(appState.error!!)
            }
        }
    }

    private fun View.showSnackBar(
        text: String,
        length: Int = Snackbar.LENGTH_LONG
    ) {
        Snackbar.make(this, text, length).show()
    }

    companion object {
        const val BUNDLE_EXTRA = "id"
        fun newInstance(film: Results): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BUNDLE_EXTRA, film)
                }
            }
        }
    }
}