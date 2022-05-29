package ru.shalashov.starwarsfilms.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.shalashov.starwarsfilms.data.appState.AppState
import ru.shalashov.starwarsfilms.databinding.FragmentCastAndCrewBinding
import ru.shalashov.starwarsfilms.databinding.FragmentDetailsBinding
import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Details
import ru.shalashov.starwarsfilms.domain.entities.Results
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCrewList.adapter = adapter
        viewModel.creditsLiveData.observe(viewLifecycleOwner) { renderDataForCredits(it) }
        val filmID = requireArguments().getParcelable<Results>(DetailsFragment.BUNDLE_EXTRA)!!.id
        viewModel.getCredits(filmID)
    }

    private fun renderDataForCredits(appState: AppState<Credits>) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val credits = appState.content
                val creditsList: MutableList<Any> = mutableListOf()
                creditsList.addAll(credits.crew)
                creditsList.addAll(credits.cast)
                adapter.setCredits(creditsList)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.fragmentCredits.showSnackBar(appState.error!!)
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
        fun newInstance(id: Int): CastAndCrewFragment {
            return CastAndCrewFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_EXTRA, id)
                }
            }
        }
    }
}