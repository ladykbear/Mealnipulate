package com.bonehill.mealnipulate

import android.content.Context
import java.io.File

class Utils
{
    companion object {
        val RecipeList= arrayListOf<String>()
        fun getDataDir(c: Context?, dir:String):File
        {
           //Recipes or List
            val letDirectory = File(c?.getFilesDir(), dir)
            if(!letDirectory.exists())
                letDirectory.mkdirs()
            return letDirectory;
        }

        fun loadRecipes(c:Context?)
        {
            RecipeList.clear()
            var d= getDataDir(c,"Recipes")
            for(  f:File in d.listFiles())
            {
               RecipeList.add(f.name)
            }
        }


    }
}