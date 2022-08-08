package com.gouhar.unsplash

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Define the Structure of the photos we get and data we get for each photo.

@JsonClass(generateAdapter = true)  // Creates a JsonClass - The way we get the data from API.
data class Photo(
    val id: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    @Json(name = "blur_hash") val blurHash: String,
    val likes: Int,
    @Json(name = "liked_by_user") val likedByUser: Boolean,
    val description: String?,
    val user: User,
    val urls: PhotoUrls,
)

@JsonClass(generateAdapter = true)
data class PhotoUrls(
    val raw: String,  // full resolution
    val full: String, // full resolution but reduce in size or quality
    val regular: String, // max 1080 pixel
    val small: String, // max 400 pixel
    val thumb: String, // max 200 pixel
)
