package com.alextroy.habrss.api


import com.alextroy.habrss.dto.Rss
import io.reactivex.Single
import retrofit2.http.GET

interface HabrApi {
    @GET("rss/hubs/all/")
    fun getArticle(): Single<Rss>
}