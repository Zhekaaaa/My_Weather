package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adaptars.WeatherAdapter
import com.example.myapplication.adaptars.WeatherModel
import com.example.myapplication.databinding.FragmentHoursBinding
import com.example.myapplication.databinding.FragmentMainBinding


class HoursFragment : Fragment() {
    lateinit var binding: FragmentHoursBinding
    lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        val list = listOf(
            WeatherModel(
                "Glubokoe", "22:00",
                "12", "34", "40", "10",
                "null", "hourse"
            ), WeatherModel(
                "Grodno", "23:00",
                "12", "23", "45", "12",
                "null", "hourse"
            )
        )
        rcView.adapter = adapter
        adapter.submitList(list)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()

    }
}
