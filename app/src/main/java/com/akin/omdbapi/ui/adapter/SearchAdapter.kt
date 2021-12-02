package com.akin.omdbapi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akin.omdbapi.data.model.Search
import com.akin.omdbapi.data.model.SearchModel
import com.akin.omdbapi.databinding.SearchItemBinding
import com.akin.omdbapi.util.loadString
import com.akin.omdbapi.util.makeBigger
import com.akin.omdbapi.util.makePlaceHolder


class SearchAdapter(var clickListener: (data: Search) -> Unit = {}) :
    RecyclerView.Adapter<SearchAdapter.HomeViewHolder>() {
    private var animeList: List<Search> = mutableListOf()


    class HomeViewHolder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.HomeViewHolder {
        val binding =
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HomeViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.apply {
            val animeListPositioned = animeList[position]
            val biggerImage = animeListPositioned.Poster.makeBigger()
            cardImage.loadString(
                biggerImage,
                makePlaceHolder(holder.binding.root.context)
            )
            cardAnimeName.text = animeListPositioned.Title
            cardAnimeEpisode.text = animeListPositioned.Type.uppercase()
            cardHome.setOnClickListener {
                clickListener(animeListPositioned)

            }


        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadAnimeData(items: List<Search>) {
        this.animeList = items
        notifyDataSetChanged()
    }


}