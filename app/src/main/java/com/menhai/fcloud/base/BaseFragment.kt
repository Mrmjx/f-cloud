package com.menhai.fcloud.base

import android.app.Activity
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.ButterKnife
import com.menhai.fcloud.R
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by mjx on 2018/3/26.
 */

open class BaseFragment : Fragment() {
    var mActivity: Activity ?= null

    private var mCompositeSubscription: CompositeSubscription? = null


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        mActivity = activity
    }

    fun initToolBar(view: View, title: String): Toolbar {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val toolbar_title = toolbar.findViewById<TextView>(R.id.toolbar_title)
        toolbar_title.text = title
        return toolbar
    }


    fun toastShow(resId: Int) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show()
    }

    fun toastShow(resId: String) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        onUnsubscribe()
    }

    fun onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription!!.unsubscribe()
        }
    }

    fun addSubscription(subscription: Subscription) {
        //        if (mCompositeSubscription == null) {
        mCompositeSubscription = CompositeSubscription()
        //        }
        mCompositeSubscription!!.add(subscription)
    }
}
