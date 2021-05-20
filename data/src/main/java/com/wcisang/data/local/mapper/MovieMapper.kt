package com.wcisang.data.local.mapper

import com.wcisang.data.local.model.MovieEntity
import com.wcisang.domain.model.Movie

object MovieMapper {

    fun mapMovieToMovieEntity(movie: Movie): MovieEntity = with(movie) {
        MovieEntity(
            id,
            adult,
            backdrop_path,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count
        )
    }

    fun mapMovieEntityToMovie(movieEntity: MovieEntity): Movie = with(movieEntity) {
        Movie(
            id,
            adult,
            backdrop_path,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count
        )
    }

}