package com.kasai.speed_whether.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kasai.speed_whether.R
import com.kasai.speed_whether.data.SimpleViewModelSolution
import com.kasai.speed_whether.databinding.SolutionBinding

const val TAG_OF_PROJECT_LIST_FRAGMENT = "ProjectListFragment"

class InitFragment : Fragment() {

    private val viewModel: SimpleViewModelSolution by viewModels()
    private lateinit var binding: SolutionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.solution, container, false) //dataBinding
        binding.lifecycleOwner = this
        Log.d("zaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaa")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel;
        Log.d("aaaaaaaaaaaaaaaaaaaaa", "ああああああああああああああああ")
    }
}
