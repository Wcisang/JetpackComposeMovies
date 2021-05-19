package com.wcisang.data.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.wcisang.domain.model.Movie

@JsonIgnoreProperties(ignoreUnknown = true)
data class MoviesResponse(
    @JsonProperty("page") val page: Int,
    @JsonProperty("results") val results: List<Movie>
)
