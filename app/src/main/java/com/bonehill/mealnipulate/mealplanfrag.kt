package com.bonehill.mealnipulate

import android.app.ActionBar
import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView


import kotlinx.android.synthetic.main.fragment_mealplanfrag.*
import kotlinx.android.synthetic.main.fragment_mealplanfrag.view.*
import org.jetbrains.anko.customView
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI

class mealplanfrag : Fragment() {

    val GO_OUT:String="Go Out"
    val LEFT_OVERS:String="Left Overs"
    val lstMeals= hashMapOf<String, String>()

    var week= arrayListOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v= inflater.inflate(R.layout.fragment_mealplanfrag, container, false);
       // return inflater.inflate(R.layout.fragment_mealplanfrag, container, false)
        val dropDownList= arrayListOf<String>()
        dropDownList.addAll(Utils.RecipeList)
        dropDownList.add(0, LEFT_OVERS)
        dropDownList.add(GO_OUT)
        val arrayAdapter = ArrayAdapter<String>(context, R.layout.recipe_spinner_item, dropDownList)

        var llparams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // This will define text view width
                LinearLayout.LayoutParams.WRAP_CONTENT
                // This will define text view height
        )
        llparams.leftMargin=5
        llparams.rightMargin=5
        llparams.bottomMargin=15
        var txtParams:LinearLayout.LayoutParams=LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
       txtParams.weight=1.0f
        var spinParams:LinearLayout.LayoutParams=LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )

        initMap()

        for(day:String in week) {
            var ll = LinearLayout(context)
            ll.orientation = LinearLayout.HORIZONTAL
            ll.layoutParams = llparams
            var txt = TextView(context)
            txt.width = 0;
            txt.layoutParams = txtParams
            txt.text = day
            txt.textSize = 25.0f

            var s = Spinner(context)
            s.layoutParams = spinParams
            s.adapter=arrayAdapter
            if(lstMeals.size>0 )
            {
                s.setSelection(arrayAdapter.getPosition(lstMeals.get(day)))
            }
            else
                s.setSelection(0)

            ll.addView(txt);
            ll.addView(s)
            v.weekDropDowns.addView(ll)

        }

        return v
    }

    fun initMap()
    {
        lstMeals.put("Monday", LEFT_OVERS)
        lstMeals.put("Tuesday", LEFT_OVERS)
        lstMeals.put("Wednesday", LEFT_OVERS)
        lstMeals.put("Thursday", LEFT_OVERS)
        lstMeals.put("Friday", LEFT_OVERS)
        lstMeals.put("Saturday", LEFT_OVERS)
        lstMeals.put("Sunday", LEFT_OVERS)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (Utils.RecipeList == null || Utils.RecipeList.size == 0) {
            alert("Would you like to add some recipes?", "No Recipes!") {
                yesButton { startActivity<recipeActivity>() }

            }.show()
        }

    }


    companion object {
        fun newInstance(): mealplanfrag = mealplanfrag()
    }
}
