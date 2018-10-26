package com.bonehill.mealnipulate

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner


import kotlinx.android.synthetic.main.fragment_mealplanfrag.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton

class mealplanfrag : Fragment() {

    var lstRecipes= arrayListOf<Recipe>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mealplanfrag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ArrayAdapter.createFromResource(
               context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinMon.adapter = adapter
        }

        ArrayAdapter.createFromResource(
                context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinTues.adapter = adapter
        }

        ArrayAdapter.createFromResource(
                context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinWed.adapter = adapter
        }

        ArrayAdapter.createFromResource(
                context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinThur.adapter = adapter
        }

        ArrayAdapter.createFromResource(
                context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinFri.adapter = adapter
        }

        ArrayAdapter.createFromResource(
               context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinSat.adapter = adapter
        }

        ArrayAdapter.createFromResource(
                context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinSun.adapter = adapter
        }



        if(lstRecipes.size==0)
        {
            alert("Would you like to add some recipes?", "No Recipes!") {
                yesButton { startActivity<recipeActivity>()  }

            }.show()
        }

    }


    companion object {
        fun newInstance(): mealplanfrag = mealplanfrag()
    }
}
