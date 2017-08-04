package com.hyundeee.app.usersearch.view.main.fragment.listener

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.text.method.Touch.onTouchEvent


/**
 * Created by jeonghyeonji on 2017. 8. 5..
 */
open class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView, listener: OnItemClickListener) : RecyclerView.OnItemTouchListener {

    lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onLongItemClick(view: View, position: Int)
    }

    val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            val child = recyclerView.findChildViewUnder(e?.x as Float, e.y)
            if (child != null && listener != null) {
                Log.d("long", "press")
                listener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child))
            }
        }
    })


    override fun onInterceptTouchEvent(recyclerView: RecyclerView?, e: MotionEvent?): Boolean {
        val childView = recyclerView?.findChildViewUnder(e?.x as Float, e.y)
        if (childView != null && listener != null && gestureDetector.onTouchEvent(e)) {
            listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
            return true
        }
        return false

    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }
}