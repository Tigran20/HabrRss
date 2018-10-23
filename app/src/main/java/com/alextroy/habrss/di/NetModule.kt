package com.alextroy.habrss.di

import com.alextroy.habrss.api.HabrApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
object NetModule {

    @Singleton
    @Provides
    internal fun provideHabrApi(retrofit: Retrofit): HabrApi {
        return retrofit.create(HabrApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        val baseURL = "https://habr.com/"
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(okHttpClient)
            .build()
    }

//    @Singleton
//    @Provides
//    internal fun provideInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val response = chain.proceed(chain.request())
//            val cacheControl = CacheControl.Builder()
//                .maxAge(1, TimeUnit.DAYS)
//                .build()
//            response.newBuilder()
//                .header("Cache-Control", cacheControl.toString())
//                .build()
//        }
//    }

//    @Singleton
//    @Provides
//    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//    }

//    @Singleton
//    @Provides
//    internal fun provideOkhttp(cache: Cache, loggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .cache(cache)
//            .addNetworkInterceptor(interceptor)
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    internal fun provideOkhttpCache(): Cache {
//        val cacheSize = 10 * 1024 * 1024
//        val httpCacheDirectory = File(application.cacheDir, "http-cache")
//        return Cache(httpCacheDirectory, cacheSize.toLong())
//    }
}