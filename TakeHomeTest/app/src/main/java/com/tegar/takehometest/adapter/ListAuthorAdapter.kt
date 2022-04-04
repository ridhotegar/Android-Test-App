package com.tegar.takehometest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tegar.takehometest.databinding.ItemRowUserBinding
import com.tegar.takehometest.model.Author
import com.tegar.takehometest.model.Home

class ListAuthorAdapter: RecyclerView.Adapter<ListAuthorAdapter.ListViewHolder>() {
    private val listUser = mutableListOf<Author>()

    fun setData(author: List<Author>) {
        listUser.addAll(author)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
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
        binding.itemDescription.text = data.experience

        Glide.with(binding.root.context)
            .load(data.authorAvatar)
            .circleCrop()
            .into(binding.itemAuthorAvatar)
    }

    override fun getItemCount(): Int = listUser.size
    class ListViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)
}