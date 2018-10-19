package com.alextroy.habrss.view.activities


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alextroy.habrss.R
import com.alextroy.habrss.api.HabrApp
import com.alextroy.habrss.dto.Entry
import com.alextroy.habrss.view.adapter.HabrAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.article_list.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HabrAdapter
    private lateinit var articleList: List<Entry>
    private val compositeDisposable = CompositeDisposable()

    private val habrApp by lazy {
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
        val searchDisposable: Disposable = habrApp.getArticle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    adapter.addAll(result.channel!!.entries)
                },
                {
                    Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
                }
            )
        compositeDisposable.add(searchDisposable)
    }
}