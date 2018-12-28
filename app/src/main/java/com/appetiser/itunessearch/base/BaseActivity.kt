package com.appetiser.itunessearch.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.appetiser.itunessearch.manager.AppRequestManager
import com.appetiser.itunessearch.manager.UserLogManager
import org.jetbrains.anko.AnkoLogger

/**
 * The super class for each activity in the app
 */
abstract class BaseActivity : AppCompatActivity(), AnkoLogger {

    protected lateinit var appRequestManager: AppRequestManager
    protected lateinit var userLogManager: UserLogManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())
        appRequestManager = AppRequestManager(getContext())
        userLogManager = UserLogManager(getContext())

        initActivity(savedInstanceState)
    }


    /**
     * Function to get the layout for the fragment
     */
    abstract fun getActivityLayout(): Int

    /**
     * Starting point for coding after initializing the layout
     */
    abstract fun initActivity(savedInstanceState: Bundle?)

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)

    }


    /**
     * Initialize toolbar if activity has a toolbar
     */
    fun initToolbar(toolbar: Toolbar?, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = title
    }

    fun initToolbar(toolbar: Toolbar?) {
        initToolbar(toolbar, "")

    }


    protected fun getContext(): Context {
        return this
    }
}