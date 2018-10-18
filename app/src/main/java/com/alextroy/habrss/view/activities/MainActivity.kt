package com.alextroy.habrss.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alextroy.habrss.R
import com.alextroy.habrss.api.HabrApp
import com.alextroy.habrss.dto.Entry
import com.alextroy.habrss.dto.Rss
import com.alextroy.habrss.view.adapter.HabrAdapter


import kotlinx.android.synthetic.main.article_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HabrAdapter
    private lateinit var articleList: List<Entry>

    private val hubrApp by lazy {
        HabrApp.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_list)

        initRecyclerView()
        getData()
    }

    private fun initRecyclerView() {
        articleList = ArrayList()
        adapter = HabrAdapter(articleList, this@MainActivity)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        hubrApp.getArticle().enqueue(object : Callback<Rss> {
            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                val entry: List<Entry>? = response.body()!!.channel!!.podcasts
                adapter.addAll(entry)
            }

            override fun onFailure(call: Call<Rss>, t: Throwable) {
            }
        })
    }
}