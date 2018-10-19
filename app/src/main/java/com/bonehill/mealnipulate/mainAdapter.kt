package com.bonehill.mealnipulate

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SampleAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> mealplanfrag.newInstance()
        1 -> listfrag.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Meal Plan"
        1 -> "Shopping List"
        else -> ""
    }

    override fun getCount(): Int = 2
}