package com.gouhar.unsplash

import retrofit2.http.GET
import retrofit2.http.Query

// How get the photos from API. - Defining it.

interface  PhotosApi {

    @GET("/photos") // Specifying the link to get the photos.
    suspend fun getPhotos( // Suspend fun - Makes it run in a different lane.
        @Query("page") page:Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("order_by") orderBy: String = "popular",
        @Query("client_id") clientId: String = "v6cyYRPIpZyFt-kLsAHhi85ZE3DahruKGWqDitNZALM"
    ): List<Photo>

}