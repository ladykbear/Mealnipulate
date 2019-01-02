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
    val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)

        val fragmentTransaction = fragmentManager.beginTransaction()
        if(Utils.RecipeList.count()==0)
        {
            //if arriving to this activity with 0 on list->go straight to edit to add recipes
            val fragment = recipeEdit.newInstance("")
            fragmentTransaction.add(R.id.recipefragment, fragment)
            fragmentTransaction.commit()

        }
        else
        {
            val fragment = recipeListFragment()
            fragmentTransaction.add(R.id.recipefragment, fragment)
            fragmentTransaction.commit()
        }



    }

    fun EditRecipe(name:String)
    {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = recipeEdit.newInstance(name)
        fragmentTransaction.replace(R.id.recipefragment, fragment)
        fragmentTransaction.commit()
    }

    companion object {

        fun recipeActivityIntent(context: Context): Intent {
            val intent = Intent(context, recipeActivity::class.java)
            return intent
        }
    }
}
