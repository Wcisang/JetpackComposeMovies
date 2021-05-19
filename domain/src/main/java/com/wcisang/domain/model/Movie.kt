package com.wcisang.domain.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Movie(
    @JsonProperty("id") val id: Int,
    @JsonProperty("adult") val adult: Boolean,
    @JsonProperty("backdrop_path") val backdrop_path: String?,
    @JsonProperty("original_language") val original_language: String,
    @JsonProperty("original_title") val original_title: String,
    @JsonProperty("overview") val overview: String,
    @JsonProperty("popularity") val popularity: Double,
    @JsonProperty("poster_path") val poster_path: String?,
    @JsonProperty("release_date") val release_date: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("video") val video: Boolean,
    @JsonProperty("vote_average") val vote_average: Double,
    @JsonProperty("vote_count") val vote_count: Int
): Parcelable