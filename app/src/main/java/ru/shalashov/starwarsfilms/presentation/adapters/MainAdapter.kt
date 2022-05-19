package ru.shalashov.starwarsfilms.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.shalashov.starwarsfilms.R
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms
import ru.shalashov.starwarsfilms.domain.entities.Results
import ru.shalashov.starwarsfilms.presentation.fragments.MainFragment

class MainAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var filmsList: List<Results>

    fun setFilm(data: PopularFilms) {
        filmsList = data.results
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filmsList[position])
    }

    override fun getItemCount(): Int = filmsList.size

    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(film: Results) {
            itemView.apply {
                findViewById<ImageView>(R.id.iv_poster).load(film.poster_path)
                findViewById<TextView>(R.id.tv_film_name).text = film.title
                findViewById<TextView>(R.id.tv_rate).text = film.vote_average.toString()
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(film)
                }
            }
        }
    }


}