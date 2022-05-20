package ru.shalashov.starwarsfilms.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.shalashov.starwarsfilms.databinding.ItemCrewBinding
import ru.shalashov.starwarsfilms.domain.entities.Cast
import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Crew
import ru.shalashov.starwarsfilms.domain.entities.Results

class CreditsAdapter(
    private var credits: Credits
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val CAST_VIEW_TYPE = 0
        const val CREW_VIEW_TYPE = 1
    }


/*    //var credits: Credits
        set(value) {
            field = value
            notifyDataSetChanged()
        }*/

    @SuppressLint("NotifyDataSetChanged")
    fun setCredits(data: Credits) {
        credits = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            CAST_VIEW_TYPE -> {
                val binding = ItemCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CastViewHolder(binding)
            }
            CREW_VIEW_TYPE -> {
                val binding = ItemCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CrewViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Wrong viewType: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CastViewHolder -> holder.bind(credits.cast[position] as Cast)
            is CrewViewHolder -> holder.bind(credits.crew[position] as Crew)
        }
    }

    override fun getItemCount(): Int =
        credits.cast.size + credits.crew.size

    override fun getItemViewType(position: Int): Int =
        when {
            credits.crew[position].javaClass.isInstance(Crew::class) -> {
                CREW_VIEW_TYPE
            }
            credits.cast[position].javaClass.isInstance(Cast::class) -> {
                CAST_VIEW_TYPE
            }
            else -> {
                3
            }
        }

    inner class CastViewHolder(private val binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            binding.ivPhoto.load("https://image.tmdb.org/t/p/w500${cast.profile_path}")
            binding.tvName.text = cast.name
            binding.tvRole.text = cast.character
        }
    }

    inner class CrewViewHolder(private val binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crew: Crew) {
            binding.ivPhoto.load("https://image.tmdb.org/t/p/w500${crew.profile_path}")
            binding.tvName.text = crew.name
            binding.tvRole.text = crew.job
        }
    }
}