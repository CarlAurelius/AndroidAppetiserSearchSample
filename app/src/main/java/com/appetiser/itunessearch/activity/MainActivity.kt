package com.appetiser.itunessearch.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Menu
import android.widget.ImageView
import com.appetiser.itunessearch.R
import com.appetiser.itunessearch.base.BaseActivity
import com.appetiser.itunessearch.data.ResultObject
import com.appetiser.itunessearch.data.SearchResponse
import com.appetiser.itunessearch.util.CurrencyUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar.view.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity() {

    lateinit var adapter: ResultAdapter

    override fun getActivityLayout(): Int {

        return R.layout.activity_main
    }

    override fun initActivity(savedInstanceState: Bundle?) {

        initToolbar(inc_appbar.toolbar, "iTunes Search")
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        adapter = ResultAdapter(R.layout.list_item_search_result, ArrayList())
        val lManager = LinearLayoutManager(getContext())

        recycler.layoutManager = lManager
        recycler.adapter = adapter

        adapter.setOnItemClickListener { pAdapter, view, position ->

            val obj = adapter.getItem(position)

            val gsonObj = Gson().toJson(obj)

            TrackDetailActivity.start(getContext(), gsonObj)
        }

        swipe_layout.setOnRefreshListener {
            request()
        }

        checkLastScreen()

    }

    /**
     * Determines the last visited screen of the user
     */
    private fun checkLastScreen() {

        adapter.setNewData(userLogManager.getUserList())
        when (userLogManager.getLastScreen()) {
            0 -> {

                if (!userLogManager.getUserList().isNotEmpty()) {
                    request()
                }
            }

            1 -> {

                TrackDetailActivity.start(getContext(), Gson().toJson(userLogManager.getDetail()))
                request()
            }
        }

    }

    /**
     * Class use to setup the adapter for the recyclerview
     */
    inner class ResultAdapter(layoutResId: Int, data: List<ResultObject>) :
            BaseQuickAdapter<ResultObject, BaseViewHolder>(layoutResId, data) {

        override fun convert(helper: BaseViewHolder, item: ResultObject) {

            val imgCover = helper.getView<ImageView>(R.id.iv_cover)


            Glide.with(getContext())
                    .load(item.artworkUrl100)
                    .apply(
                            RequestOptions()
                                    .placeholder(R.mipmap.ic_launcher)
                                    .placeholder(R.drawable.not_found)
                    )
                    .into(imgCover)

            if (TextUtils.isEmpty(item.trackName)) {
                helper.setText(R.id.tv_name, "N/A")
            } else {
                helper.setText(R.id.tv_name, item.trackName)
            }


            helper.setText(R.id.tv_genre, item.primaryGenreName)
                    .setText(
                            R.id.tv_collection_price,
                            "Collection: " + CurrencyUtil.checkIfLessThanZero(item.collectionPrice, item.currency)
                    )
                    .setText(
                            R.id.tv_collection_hd_price,
                            "Collection HD: " + CurrencyUtil.checkIfLessThanZero(item.collectionHdPrice, item.currency)
                    )
                    .setText(
                            R.id.tv_track_price,
                            "Track: " + CurrencyUtil.checkIfLessThanZero(item.trackPrice, item.currency)
                    )
                    .setText(
                            R.id.tv_track_hd_price,
                            "Track HD: " + CurrencyUtil.checkIfLessThanZero(item.trackHdPrice, item.currency)
                    )
                    .setText(
                            R.id.tv_track_rental_price,
                            "Track Rental: " + CurrencyUtil.checkIfLessThanZero(item.trackRentalPrice, item.currency)
                    )
                    .setText(
                            R.id.tv_track_rental_hd_price,
                            "Track Rental HD: " + CurrencyUtil.checkIfLessThanZero(item.trackHdRentalPrice, item.currency)
                    )

        }

    }


    /**
     * Request the data from the api
     */
    private fun request() {
        val params = HashMap<String, Any>()
        params["term"] = "star"
        params["amp;country"] = "au"
        params["amp;media"] = "movie"

        val req = appRequestManager.service
                .search(params)

        loading(true)
        req.enqueue(object : Callback<SearchResponse> {

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                loading(false)

                toast("Something went wrong")
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {

                loading(false)
                adapter.setNewData(response.body()?.results!!)
            }
        })
    }

    private fun loading(flag: Boolean) {
        swipe_layout.isRefreshing = flag
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val menuInflater = menuInflater
//        menuInflater.inflate(R.menu.home_menu, menu)
//
//        val searchItem = menu?.findItem(R.id.action_search)
//
//        var searchView: SearchView? = null
//        if (searchItem != null) {
//            searchView = searchItem.actionView as SearchView
//        }
//
////        val searchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
////        searchView?.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))
//        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//
//                return false
//            }
//        })
        return super.onCreateOptionsMenu(menu)
    }


    override fun onResume() {
        super.onResume()

        userLogManager.saveLastScreen(0)

    }
}
