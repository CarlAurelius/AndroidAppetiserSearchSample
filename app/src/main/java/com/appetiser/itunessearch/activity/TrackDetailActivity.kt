package com.appetiser.itunessearch.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.appetiser.itunessearch.R
import com.appetiser.itunessearch.base.BaseActivity
import com.appetiser.itunessearch.data.ResultObject
import com.appetiser.itunessearch.util.CurrencyUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_track_detail.*
import kotlinx.android.synthetic.main.layout_app_bar.view.*


class TrackDetailActivity : BaseActivity() {


    companion object {

        val keyResultData = "key.result.data"

        fun start(context: Context, result: String) {
            val intent = Intent(context, TrackDetailActivity::class.java)
            intent.putExtra(keyResultData, result)

            context.startActivity(intent)
        }
    }

    override fun getActivityLayout(): Int {

        return R.layout.activity_track_detail
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity(savedInstanceState: Bundle?) {

//        result = Parcels.unwrap(intent.getParcelableExtra(keyResultData))
        val obj = Gson().fromJson(intent.getStringExtra(keyResultData), ResultObject::class.java)


        userLogManager.saveLastScreen(1)
        userLogManager.saveDetail(obj)

        var strName = obj?.trackName

        if (TextUtils.isEmpty(strName)) {
            strName = "N/A"
        }

        initToolbar(inc_appbar.toolbar, "Track Details")

        tv_name.text = strName
        tv_genre.text = obj.primaryGenreName
        tv_collection_price.text = "Collection: " + CurrencyUtil.checkIfLessThanZero(obj.collectionPrice, obj.currency)
        tv_collection_hd_price.text = "Collection HD: " + CurrencyUtil.checkIfLessThanZero(obj.collectionHdPrice, obj.currency)
        tv_track_price.text = "Track: " + CurrencyUtil.checkIfLessThanZero(obj.trackPrice, obj.currency)
        tv_track_hd_price.text = "Track HD: " + CurrencyUtil.checkIfLessThanZero(obj.trackHdPrice, obj.currency)
        tv_track_rental_price.text = "Track Rental: " + CurrencyUtil.checkIfLessThanZero(obj.trackRentalPrice, obj.currency)
        tv_track_rental_hd_price.text = "Track Rental HD: " + CurrencyUtil.checkIfLessThanZero(obj.trackHdRentalPrice, obj.currency)


        var strDesc = obj.longDescription

        if (TextUtils.isEmpty(strDesc)) {
            strDesc = "No description available"
        }
        tv_desc.text = strDesc


        Glide.with(getContext())
                .load(obj.artworkUrl100)
                .apply(
                        RequestOptions()
                                .placeholder(R.mipmap.ic_launcher)
                                .placeholder(R.drawable.not_found)
                )
                .into(iv_cover)
    }

    override fun onResume() {
        super.onResume()


    }
}