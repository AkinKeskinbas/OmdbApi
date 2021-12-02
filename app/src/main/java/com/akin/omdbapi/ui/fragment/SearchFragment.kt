package com.akin.omdbapi.ui.fragment

import android.os.Build
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.model.Search
import com.akin.omdbapi.databinding.FragmentSearchBinding
import com.akin.omdbapi.domain.SearchViewModel
import com.akin.omdbapi.ui.adapter.SearchAdapter
import com.akin.omdbapi.ui.fragment.basefragment.BaseFragment
import com.akin.omdbapi.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter = SearchAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchRc()
        searchViewListener()

    }

    private fun searchViewListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextSubmit(query: String?): Boolean {

                getMoviesData(query!!)
                hideKeyboard()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText.isNullOrEmpty()) {
                    setUiBeforeSearch()

                } else {
                    binding.apply {
                        emptyText.visibility = View.GONE
                        emptyText2.visibility = View.GONE
                    }

                }
                return true
            }
        })

    }

    private fun getMoviesData(title: String) {
        searchViewModel.getMoviesByQuery(title).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    setLoadingUi()
                }
                Resource.Status.SUCCESS -> {
                    setSuccessUi()
                    searchAdapter.loadAnimeData(response.data?.Search!!)

                }
                Resource.Status.ERROR -> {
                    setErrorUi()
                }
            }
        })
    }

    private fun initSearchRc() {
        searchAdapter.clickListener = { data ->
            navigateToDetail(data.imdbID)
        }
        binding.rcSearch.adapter = searchAdapter

    }

    private fun navigateToDetail(imdbId: String) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(imdbId)
        findNavController().navigate(action)
    }

    private fun setLoadingUi() {
        binding.apply {
            progressCircular.visibility = View.VISIBLE
            progressCircular.playAnimation()
            emptyText.visibility = View.GONE
            emptyText2.visibility = View.GONE
            rcSearch.visibility = View.GONE

        }

    }

    private fun setSuccessUi() {
        binding.apply {
            progressCircular.visibility = View.GONE
            errorImage.visibility = View.GONE
            rcSearch.visibility = View.VISIBLE
            progressCircular.cancelAnimation()
        }

    }

    private fun setErrorUi() {

        binding.apply {
            errorImage.visibility = View.VISIBLE
            progressCircular.visibility = View.GONE
            errorImage.playAnimation()
        }

    }

    private fun setUiBeforeSearch() {
        binding.apply {
            emptyText.visibility = View.VISIBLE
            emptyText2.visibility = View.VISIBLE
            rcSearch.visibility = View.GONE
            errorImage.visibility = View.GONE
        }
    }


}