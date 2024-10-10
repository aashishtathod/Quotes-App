package com.aashishtathod.dev.quotesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aashishtathod.dev.quotesapp.databinding.CellQuoteBinding
import com.aashishtathod.dev.quotesapp.domain.model.QuoteModel

class QuotesAdapter : PagingDataAdapter<QuoteModel, QuotesAdapter.QuoteViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CellQuoteBinding.inflate(inflater, parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class QuoteViewHolder(
        private val binding: CellQuoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: QuoteModel) {
            binding.quote.text = data.content
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<QuoteModel>() {
            override fun areItemsTheSame(oldItem: QuoteModel, newItem: QuoteModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: QuoteModel, newItem: QuoteModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}