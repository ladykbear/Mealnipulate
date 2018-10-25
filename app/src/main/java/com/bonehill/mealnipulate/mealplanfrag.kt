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
            val builder = AlertDialog.Builder(context)
            builder.setTitle("No Recipes!")
            builder.setMessage("Would you like to enter some recipes?")
            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("YES"){dialog, which ->
                // Do something when user press the positive button

            }

            // Display a negative button on alert dialog
            builder.setNegativeButton("No"){dialog,which ->

            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }

    companion object {
        fun newInstance(): mealplanfrag = mealplanfrag()
    }
}
