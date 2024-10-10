package com.aashishtathod.dev.quotesapp.data.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aashishtathod.dev.quotesapp.data.source.remote.service.QuotesAPI
import com.aashishtathod.dev.quotesapp.domain.mapper.toQuoteModelList
import com.aashishtathod.dev.quotesapp.domain.model.QuoteModel

class QuotesPagingDataSource(
    private val api: QuotesAPI
) : PagingSource<Int, QuoteModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteModel> {
        return try {
            val currentPage = params.key ?: 1
            val quotes = api.getQuoteList(currentPage)
            LoadResult.Page(
                data = quotes.toQuoteModelList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (quotes.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.d("Debug -> ", "${e.localizedMessage}")
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, QuoteModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}