package com.tegar.takehometest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tegar.takehometest.databinding.ItemRowUserBinding
import com.tegar.takehometest.model.Home

class ListHomeAdapter: RecyclerView.Adapter<ListHomeAdapter.ListViewHolder>() {
    private val listUser = mutableListOf<Home>()

    fun setData(home: List<Home>) {
        listUser.addAll(home)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val binding = holder.binding
        val data = listUser[position]

        binding.itemAuthor.text = data.author
        binding.itemDescription.text = data.description

        Glide.with(binding.root.context)
            .load(data.authorAvatar)
            .circleCrop()
            .into(binding.itemAuthorAvatar)
    }

    override fun getItemCount(): Int = listUser.size
    class ListViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)
}