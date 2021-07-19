package com.kasai.speed_whether.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kasai.speed_whether.R
import com.kasai.speed_whether.viewModel.SimpleViewModelSolution
import com.kasai.speed_whether.databinding.SolutionBinding
import com.kasai.speed_whether.model.WeatherInfo
import com.kasai.speed_whether.viewModel.WeatherInfoViewModel

const val TAG_OF_PROJECT_LIST_FRAGMENT = "ProjectListFragment"

class InitFragment : Fragment() {

    private val viewModel: SimpleViewModelSolution by viewModels()
    private lateinit var binding: SolutionBinding

    private val weatherInfoViewModel: WeatherInfoViewModel by viewModels()
    //private lateinit var weatherInfoBinding: SolutionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.solution, container, false) //dataBinding
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel;
        observeWeatherInfoViewModel(weatherInfoViewModel)
    }

    //observe開始
    private fun observeWeatherInfoViewModel(viewModel: WeatherInfoViewModel) {

        //データをSTARTED かRESUMED状態である場合にのみ、アップデートするように、LifecycleOwnerを紐付け、ライフサイクル内にオブザーバを追加
        viewModel.weatherInfoLiveData.observe(viewLifecycleOwner, Observer { weatherInfo ->
            if (weatherInfo != null) {
                binding.wheatherInfoViewModel = viewModel
                Log.d("AAAAAAAAAAAAAAAAAAAA",
                    (viewModel.weatherInfoLiveData.value as WeatherInfo).current.temp.toString() + "" + (viewModel.weatherInfoLiveData.value as WeatherInfo).current.clouds.toString()
                )
            }
        })
    }
}
