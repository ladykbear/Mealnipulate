package com.bonehill.mealnipulate

class Recipe()
{
    var recipeName=""
    var recipeIngredients= arrayListOf<String>()

    fun updateIngredients(newList:ArrayList<String>){
        recipeIngredients=newList
    }
    fun updateName(name:String)
    {
        recipeName=name;
    }
}