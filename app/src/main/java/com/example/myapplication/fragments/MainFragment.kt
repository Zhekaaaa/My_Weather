package com.example.myapplication.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import com.example.myapplication.adaptars.VpAdapter
import com.example.myapplication.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

const val ApiKey = "33c871dcb48b45fa824132430241701"

class MainFragment : Fragment() {
    private val fList = listOf(
        HoursFragment.newInstance(),
        DeysFragment.newInstance()
    )
    private val tList = listOf(
        "HOURS",
        "DEYS"
    )

    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissions()
        init()
        requestWeatherData("London")
    }

    private fun init() = with(binding) {
        val adapter = VpAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout, vp) { tab, pos ->
            tab.text = tList[pos]
        }.attach()
    }

    private fun permissionsListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "Permission $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermissions() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionsListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    fun requestWeatherData(city: String) {
        val url = "https://api.weatherapi.com/v1/current.json" + "?key=$ApiKey&q=$city&aqi=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET, url, { result ->
                Log.d("MyLog","MyLog1:$result")
            },
            { error ->
            })
        queue.add(request)

    }
}

