package com.tegar.takehometest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tegar.takehometest.adapter.ListHomeAdapter
import com.tegar.takehometest.databinding.FragmentHomeBinding
import com.tegar.takehometest.model.Home
import java.io.IOException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val listUser = mutableListOf<Home>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val homeAdapter = ListHomeAdapter()
        binding.user.layoutManager = LinearLayoutManager(context)
        binding.user.adapter = homeAdapter
        binding.user.setHasFixedSize(true)

        val home = getJson()
        for ((index, value) in home.withIndex()) {
            val home = Home(
                value.category.toString(),
                value.author.toString(),
                value.authorAvatar.toString(),
                value.title.toString(),
                value.description.toString(),
                value.publishedDate.toString(),
                value.banner.toString(),
                value.like?.toInt()
            )
            listUser.add(home)
            Log.d("value with index $index", home.toString())
        }

        homeAdapter.setData(listUser)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getJson(): List<Home> {
        val string: String?
        try {
            string = activity?.assets!!.open("home.json").bufferedReader().use { it.readText() }
            return Gson().fromJson(string, Array<Home>::class.java).toList()

        }catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
    }

}