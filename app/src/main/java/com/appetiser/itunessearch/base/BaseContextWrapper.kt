package com.appetiser.itunessearch.base

import android.content.Context
import android.content.ContextWrapper
import com.appetiser.itunessearch.manager.AppRequestManager
import org.jetbrains.anko.AnkoLogger

open class BaseContextWrapper(var context : Context) : ContextWrapper(context), AnkoLogger
{
    protected var appRequestManager: AppRequestManager = AppRequestManager(context)

}