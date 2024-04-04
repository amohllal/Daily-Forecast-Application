package com.example.dailyforecast.forecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dailyforecast.R
import com.example.dailyforecast.databinding.FragmentForecastBinding
import com.example.dailyforecast.forecast.adapter.DailyForecastAdapter
import com.example.dailyforecast.forecast.viewmodel.ForecastViewModel
import com.example.dailyforecast.model.City
import com.example.dailyforecast.util.ForecastViewState
import com.example.dailyforecast.util.loadJSONFromAsset
import com.example.dailyforecast.util.parseJSON
import com.example.domain.model.DailyForecastResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private val forecastViewModel: ForecastViewModel by viewModels()
    private var city: City = City()
    private lateinit var dailyForecastAdapter: DailyForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readCities()
        initViews()
        observeData()
        initListener()
    }

    private fun readCities() {
        val jsonString = getString(R.string.cities_file_name).loadJSONFromAsset(requireContext())
        if (!jsonString.isNullOrEmpty()) {
            val cities = jsonString.parseJSON()
            setupSpinner(cities)
        }
    }

    private fun initViews() {
        dailyForecastAdapter = DailyForecastAdapter(arrayListOf())
        binding.forecastRv.adapter = dailyForecastAdapter
    }

    private fun setupSpinner(cities: List<City>) {
        val cityNames = cities.map { it.cityNameEn }
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, cityNames)
        binding.spinner.adapter = adapter


        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                city = cities[position]
                forecastViewModel.fetchDailyForecast(
                    lat = city.lat,
                    long = city.lon,
                    countyCode = city.country
                )

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun observeData() {
        forecastViewModel.forecastViewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ForecastViewState.Loading -> handleLoading(state.isLoading)

                is ForecastViewState.Error -> handleError(state.errorMessage)

                is ForecastViewState.Success -> handleSuccess(state.data)

                is ForecastViewState.Warning -> handleWarning(state.data)
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        if (isLoading) {
            binding.forecastRv.isVisible = false
            binding.errorLayout.isVisible = false
        }
    }

    private fun handleError(errorMessage: String) {
        binding.errorLayout.isVisible = true
        binding.forecastRv.isVisible = false
        binding.noDataTv.text = "${getString(R.string.something_went_wrong)}: $errorMessage"
    }

    private fun handleSuccess(data: List<DailyForecastResponse>) {
        handleDailyForecastList(data)
    }

    private fun handleWarning(data: List<DailyForecastResponse>) {
        handleDailyForecastList(data)
        Toast.makeText(requireContext(), getString(R.string.not_accurate_data), Toast.LENGTH_LONG)
            .show()
    }

    private fun handleDailyForecastList(data: List<DailyForecastResponse>) {
        binding.errorLayout.isVisible = false
        binding.forecastRv.isVisible = true
        dailyForecastAdapter.list.clear()
        dailyForecastAdapter.list.addAll(data)
        dailyForecastAdapter.notifyDataSetChanged()
    }

    private fun initListener() {
        binding.retryBtn.setOnClickListener {
            forecastViewModel.fetchDailyForecast(
                lat = city.lat,
                long = city.lon,
                countyCode = city.country
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}