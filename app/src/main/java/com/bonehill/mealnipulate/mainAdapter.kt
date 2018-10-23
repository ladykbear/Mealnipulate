package com.bonehill.mealnipulate


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class MainAdapter(fm: FragmentManager, parentActivity: MainActivity) : FragmentPagerAdapter(fm) {

    val parent=parentActivity

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> mealplanfrag.newInstance()
        1 -> listfrag.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {

        0 ->"Meal Plan"//parent.resources.getString(R.string.MealPlan)
        1 ->"Shopping List"// parent.resources.getString(R.string.ShoppingList)
        else -> ""
    }

    override fun getCount(): Int = 2
}