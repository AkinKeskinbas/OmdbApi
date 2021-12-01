package com.akin.omdbapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akin.omdbapi.R
import com.akin.omdbapi.databinding.FragmentDetailBinding
import com.akin.omdbapi.ui.fragment.basefragment.BaseFragment


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding:: inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}