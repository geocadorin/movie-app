package com.example.movie.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.core.domain.model.MovieSearch
import com.example.movie.movie_popular_feature.data.mapper.toMovie
import com.example.movie.search_movie_feature.data.mapper.toMovieSearc
import com.example.movie.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import okio.IOException
import retrofit2.HttpException

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource
) : PagingSource<Int, MovieSearch>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getSearchMovies(pageNumber, query)
            val movies = response.results

            LoadResult.Page(
                data = movies.toMovieSearc(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if(movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}