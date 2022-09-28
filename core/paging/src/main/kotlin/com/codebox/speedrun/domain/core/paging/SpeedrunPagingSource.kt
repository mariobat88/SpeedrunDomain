package com.codebox.speedrun.domain.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codebox.speedrun.domain.data.pagination.PaginationModel

class SpeedrunPagingSource<T : Any>(
    private val loadPage: suspend (size: Int, page: Int) -> PaginationModel<T>,
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val offset = params.key ?: 0
            val max = params.loadSize

            val response = loadPage(offset, max)

            val items = response.data
            val currentOffset = response.pagination.offset
            val size = response.pagination.size

            if (items.isEmpty() || items.size < size) {
                LoadResult.Page(
                    data = items,
                    prevKey = null,
                    nextKey = null
                )
            } else {
                LoadResult.Page(
                    data = items,
                    prevKey = (currentOffset - max).coerceAtLeast(0),
                    nextKey = currentOffset + max
                )
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
