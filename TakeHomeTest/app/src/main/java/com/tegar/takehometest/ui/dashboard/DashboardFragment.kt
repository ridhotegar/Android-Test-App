package com.tegar.takehometest.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tegar.takehometest.adapter.ListAuthorAdapter
import com.tegar.takehometest.adapter.ListHomeAdapter
import com.tegar.takehometest.databinding.FragmentDashboardBinding
import com.tegar.takehometest.model.Author
import com.tegar.takehometest.model.Home
import java.io.IOException

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val listUser = mutableListOf<Author>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val authorAdapter = ListAuthorAdapter()
        binding.user.layoutManager = LinearLayoutManager(context)
        binding.user.adapter = authorAdapter
        binding.user.setHasFixedSize(true)

        val author = getJson()
        for ((index, value) in author.withIndex()) {
            val author = Author(
                value.author.toString(),
                value.authorAvatar.toString(),
                value.experience.toString()
            )
            listUser.add(author)
        }

        authorAdapter.setData(listUser)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getJson(): List<Author> {
        val string: String?
        try {
            string = activity?.assets!!.open("author.json").bufferedReader().use { it.readText() }
            return Gson().fromJson(string, Array<Author>::class.java).toList()

        }catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
    }
}