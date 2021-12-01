package com.akin.omdbapi.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akin.omdbapi.data.model.Search
import com.akin.omdbapi.databinding.FragmentSearchBinding
import com.akin.omdbapi.domain.SearchViewModel
import com.akin.omdbapi.ui.adapter.SearchAdapter
import com.akin.omdbapi.ui.fragment.basefragment.BaseFragment
import com.akin.omdbapi.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val list: ArrayList<Search> by lazy { arrayListOf() }
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter = SearchAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSonEklenenRc()
        searchViewListener()

    }

    private fun searchViewListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextSubmit(query: String?): Boolean {
                list.clear()
                if (query?.length!! > 2) {
                    getMoviesData(query)
                    hideKeyboard()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText.isNullOrEmpty()) {
                    binding.apply {
                        emptyText.visibility = View.VISIBLE
                        emptyText2.visibility = View.VISIBLE
                        rcSearch.visibility = View.GONE
                    }
                    list.clear()

                } else {
                    list.clear()
                    binding.apply {
                        emptyText.visibility = View.GONE
                        emptyText2.visibility = View.GONE
                    }

                }
                return true
            }
        })

    }

    private fun getMoviesData(query: String) {
        searchViewModel.getMovies(query)
        searchViewModel.response.observe(viewLifecycleOwner, { response ->
            if (!response.Search.isNullOrEmpty()) {
                list.clear()
                list.addAll(response.Search)
                searchAdapter.loadAnimeData(list)
            }

        })
        searchViewModel.isLoading.observe(viewLifecycleOwner, {
            if (it) {
                binding.apply {
                    progressCircular.visibility = View.VISIBLE
                    rcSearch.visibility = View.INVISIBLE
                }
            } else {
                binding.apply {
                    progressCircular.visibility = View.GONE
                    rcSearch.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun initSonEklenenRc() {
        searchAdapter.clickListener = { data ->
            navigateToDetail(data.imdbID)
        }
        binding.rcSearch.adapter = searchAdapter

    }
    private fun navigateToDetail(imdbId:String){
        val action =
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(imdbId)
        findNavController().navigate(action)
    }


}