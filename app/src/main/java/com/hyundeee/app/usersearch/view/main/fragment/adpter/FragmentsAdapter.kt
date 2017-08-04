package com.hyundeee.app.usersearch.view.main.fragment.adpter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter
import com.hyundeee.app.usersearch.view.main.fragment.MainFragment



/**
 * Created by jeonghyeonji on 2017. 8. 3..
 */
class FragmentsAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    var mFragmentCache: LinkedHashMap<Int, Fragment> = LinkedHashMap()

    override fun onDestroyItem(position: Int, fragment: Fragment?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int, savedState: Fragment.SavedState?): Fragment {
        val f = if (mFragmentCache.containsKey(position))
            mFragmentCache[position]
        else
            MainFragment()

        mFragmentCache.put(position, f as Fragment);
        return f
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}