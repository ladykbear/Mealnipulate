package com.bonehill.mealnipulate

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_recipe.*
import org.jetbrains.anko.startActivity

class recipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolmenu, menu)

        toolbar.setOnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.newRecipe -> {
                    startActivity<recipeActivity>()
                }
            }
            true
        }
        return true
    }

    companion object {

        fun recipeActivityIntent(context: Context): Intent {
            val intent = Intent(context, recipeActivity::class.java)
            return intent
        }
    }
}
