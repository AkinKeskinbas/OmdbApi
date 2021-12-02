package com.akin.omdbapi.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.akin.omdbapi.R
import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.model.Search
import com.akin.omdbapi.databinding.FragmentDetailBinding
import com.akin.omdbapi.domain.DetailViewModel
import com.akin.omdbapi.domain.SearchViewModel
import com.akin.omdbapi.ui.fragment.basefragment.BaseFragment
import com.akin.omdbapi.util.loadString
import com.akin.omdbapi.util.makeBigger
import com.akin.omdbapi.util.makePlaceHolder
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMoviesData()

    }


    private fun getMoviesData() {
        detailViewModel.searchMovieById(args.imdbId).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    setLoadingUi()
                }
                Resource.Status.SUCCESS -> {
                    val biggerImage = response.data?.Poster?.makeBigger()
                    setSuccessUi(
                        response.data?.Title.toString(),
                        biggerImage.toString(),
                        response.data?.Genre.toString(),
                        response.data?.Plot.toString(),
                        response.data?.imdbRating.toString(),
                        response.data?.Writer.toString(),
                        response.data?.Actors.toString(),
                        response.data?.Released.toString(),
                    )

                }
                Resource.Status.ERROR -> {
                    setErrorUi(response.message.toString())
                }
            }
        })
    }

    private fun setErrorUi(errorMessage: String) {
        println(errorMessage)
    }

    @SuppressLint("SetTextI18n")
    private fun setSuccessUi(
        name: String,
        image: String,
        genres: String,
        detail: String,
        rating: String,
        writer: String,
        actors: String,
        released:String
    ) {
        binding.apply {
            progressCircular.visibility = View.GONE
            cardMovieNameDetail.text = name
            genresText.text = genres
            detailImage.loadString(image, makePlaceHolder(requireContext()))
            ratingText.text = "Rating: $rating"
            writerText.text = "Writer: $writer"
            actorsText.text = "Actors: $actors"
            releasedText.text = "Released: $released"
            BottomSheetBehavior.from(binding.bottomSheet).apply {
                peekHeight = 100
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            plotText.text = detail
            showDetailsButton.setOnClickListener {
                BottomSheetBehavior.from(binding.bottomSheet).apply {
                    if (this.state != BottomSheetBehavior.STATE_EXPANDED) {
                        this.state = BottomSheetBehavior.STATE_EXPANDED
                    }

                }
            }
        }

    }

    private fun setLoadingUi() {
        binding.apply {
            progressCircular.visibility = View.VISIBLE

        }

    }


}