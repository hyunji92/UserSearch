package com.hyundeee.app.usersearch.view.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter
import com.hyundeee.app.usersearch.view.main.fragment.MainFragment
import android.os.Bundle





/**
 * Created by jeonghyeonji on 2017. 8. 3..
 */
class FragmentsAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    var fragmentCache: LinkedHashMap<Int, Fragment> = LinkedHashMap()

    override fun getItem(position: Int, savedState: Fragment.SavedState?): Fragment {
        val f:Fragment = if (fragmentCache.containsKey(position))
            fragmentCache[position]!!
        else
            MainFragment()

        if (savedState == null || f.arguments == null){
            val bundle = Bundle()
            bundle.putInt("index",position)
            f.apply {
                arguments = bundle
            }
        } else if (!fragmentCache.containsKey(position)){
            f.setInitialSavedState(savedState)
        }

        fragmentCache.put(position, f )
        return f
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onDestroyItem(position: Int, p1: Fragment?) {
        while (fragmentCache.size > 2){
            val keys = fragmentCache.keys.toIntArray()
            fragmentCache.remove(keys[0])
        }
    }

}