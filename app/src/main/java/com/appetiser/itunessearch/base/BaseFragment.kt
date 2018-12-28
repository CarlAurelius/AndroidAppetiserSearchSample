package com.appetiser.itunessearch.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger


/**
 * The super class for each fragment in the app
 */

abstract class BaseFragment: Fragment(), AnkoLogger
{
    lateinit var act: BaseActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(getFragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        act = activity as BaseActivity

        initFragment(view, savedInstanceState)
    }

    /**
     * Function to get the layout for the fragment
     */
    abstract fun getFragmentLayout(): Int

    /**
     * Starting point for coding after initializing the layout
     */
    abstract fun initFragment(view: View, savedInstanceState: Bundle?)
}