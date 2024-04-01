package com.example.animotionapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animotionapps.databinding.ItemRowBinding

class ListAdapter(private val listAnimotion: List<AnimotionData>) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ItemRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listAnimotion.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listAnimotion[position]
        with(holder.binding) {
            Glide.with(root.context)
                .load(photo)
                .into(imgItemPhoto)
            tvItemName.text = name
            tvItemDescription.text = description
        }
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listAnimotion[position])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: AnimotionData)
    }

    class ListViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)
}
