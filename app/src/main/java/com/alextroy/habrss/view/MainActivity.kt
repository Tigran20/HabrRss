package com.alextroy.habrss.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alextroy.habrss.R
import com.alextroy.habrss.api.HabrApi
import com.alextroy.habrss.di.App
import com.alextroy.habrss.dto.Entry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.article_list.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HabrAdapter
    private lateinit var articleList: List<Entry>
    private lateinit var disposable: Disposable

    @Inject
    lateinit var habrApi: HabrApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        (application as App).getComponent().inject(this)

        initRecyclerView()
        getData()
        updateData()
    }

    private fun initRecyclerView() {
        articleList = ArrayList()
        adapter = HabrAdapter(articleList, this@MainActivity)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun updateData() {
        swipe_refresh_layout.setOnRefreshListener {
            getData()
            swipe_refresh_layout.isRefreshing = false
            Toast.makeText(this, getString(R.string.updated), Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData() {
        disposable = habrApi.getArticle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    adapter.addAll(result.channel!!.entries)
                },
                {
                    Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_SHORT).show()
                }
            )
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}