package com.alextroy.habrss.api


import com.alextroy.habrss.dto.Rss
import retrofit2.Call
import retrofit2.http.GET

interface HabrApi {
    @GET("rss/hubs/all/")
    fun getArticle(): Call<Rss>
}