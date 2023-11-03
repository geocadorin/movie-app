package com.example.movie.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.core.domain.model.Movie
import com.example.movie.core.movie_popular_feature.data.mapper.toMovie
import com.example.movie.core.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import okio.IOException
import retrofit2.HttpException

class MoviePagingSource(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val ancorPage = state.closestPageToPosition(it)
            ancorPage?.prevKey?.plus(LIMIT) ?: ancorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getPopularMovies(page = pageNumber)
            val movies = response.results

            LoadResult.Page(
                data = movies.toMovie(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if(movies.isEmpty()) null else pageNumber + 1
            )


        } catch (exeption: IOException){
            exeption.printStackTrace()
            return LoadResult.Error(exeption)
        } catch (exeption: HttpException){
            exeption.printStackTrace()
            return LoadResult.Error(exeption)
        }
    }

    companion object{
        private const val LIMIT = 50
    }

}