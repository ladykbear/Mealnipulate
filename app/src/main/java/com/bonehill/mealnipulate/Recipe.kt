package com.bonehill.mealnipulate

class Recipe(name: String, ingredients: ArrayList<String>)
{
    var recipeName=name
    var recipeIngredients=ingredients;

    fun updateIngredients(newList:ArrayList<String>){
        recipeIngredients=newList
    }
}