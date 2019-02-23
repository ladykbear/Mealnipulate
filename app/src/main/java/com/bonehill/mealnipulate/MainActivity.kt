package com.bonehill.mealnipulate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class MainActivity : AppCompatActivity () {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val pageradapter = MainAdapter(supportFragmentManager,this)
        pager.adapter=pageradapter;
        tab_layout.setupWithViewPager(pager)

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }

    override fun onResume() {
        super.onResume()
        Utils.loadRecipes(this)

    }
   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolmenu, menu)

       toolbar.setOnMenuItemClickListener { item: MenuItem? ->

           when (item!!.itemId) {
               R.id.addRecipe -> {
                   startActivity<recipeActivity>()
               }
           }
           true
       }
        return true
    }
}
