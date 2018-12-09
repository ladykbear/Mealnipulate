package com.bonehill.mealnipulate

class Recipe()
{
    var Name=""
    var Ingredients= arrayListOf<String>()

    fun updateIngredients(newList:ArrayList<String>){
        Ingredients=newList
    }
    fun updateName(name:String)
    {
        Name=name;
    }
}