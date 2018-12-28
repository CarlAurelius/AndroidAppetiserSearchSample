package com.appetiser.itunessearch.manager

import android.content.Context
import com.appetiser.itunessearch.base.BaseContextWrapper
import com.appetiser.itunessearch.data.ResultObject
import com.orhanobut.hawk.Hawk

class UserLogManager(context: Context) : BaseContextWrapper(context) {

    private val keyLastScreen = "key.last.screen"
    private val keyUserList = "key.user.list"
    private val keyLastObj = "key.last.obj"

    /**
     * @param screen (0 = list of movies, 1 = detail screen)
     *
     */
    fun saveLastScreen(screen: Int) {
        Hawk.put(keyLastScreen, screen)
    }

    /**
     * @return (1 = list of movies, 2 = detail screen)
     */
    fun getLastScreen() : Int {
        return Hawk.get(keyLastScreen, 0)
    }


    /**
     * Save user recent list
     * @param list = is the list of the recent list retrieve from the API
     */
    fun saveUserList(list: List<ResultObject>) {
        Hawk.put(keyUserList, list)
    }

    /**
     * Retrieve the recent list fetch from the API
     */
    fun getUserList(): List<ResultObject> {
        return Hawk.get(keyUserList, ArrayList())
    }

    /**
     * Save user recent detail page data
     * @param obj = is the data to be stored in the app
     */
    fun saveDetail(obj: ResultObject) {
        Hawk.put(keyLastObj, obj)
    }

    /**
     * Retrive the object from the recent screen visited
     */
    fun getDetail(): ResultObject {
        return Hawk.get(keyLastObj)
    }

}