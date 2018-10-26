package com.bonehill.mealnipulate

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class recipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
    }

    companion object {

        fun recipeActivityIntent(context: Context): Intent {
            val intent = Intent(context, recipeActivity::class.java)
            return intent
        }
    }
}
