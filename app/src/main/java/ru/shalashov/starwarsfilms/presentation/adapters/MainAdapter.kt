package ru.shalashov.starwarsfilms.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.shalashov.starwarsfilms.databinding.ItemFilmBinding
import ru.shalashov.starwarsfilms.domain.entities.Results

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var filmsList: List<Results> = listOf()
    var onItemViewClickListener: ((Results) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setFilm(data: List<Results>) {
        filmsList = data.sortedBy { it.title }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filmsList[position])
        holder.itemView.setOnClickListener {
            onItemViewClickListener?.invoke(filmsList[position])
        }
    }

    override fun getItemCount(): Int = filmsList.size

    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class MainViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Results) {
            binding.ivPoster.load("https://image.tmdb.org/t/p/w500${film.poster_path}")
            binding.tvFilmName.text = film.title
            binding.tvReleaseDate.text = film.release_date
            binding.tvRate.text = film.vote_average.toString()
        }
    }
}


