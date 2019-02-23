package com.bonehill.mealnipulate

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.fragment_recipe_edit.*
import kotlinx.android.synthetic.main.fragment_recipe_edit.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert


import java.lang.Exception
import com.google.gson.Gson
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [recipeEdit.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [recipeEdit.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class recipeEdit : Fragment() {
    // TODO: Rename and change types of parameters
    private var recipename=""
    private var ingreds= arrayListOf<Ingredient>()
    private var lvAdapter:IngredAdapter?=null
    private var listener: OnFragmentInteractionListener? = null
    private var editRecipe:Recipe?=null
    private var currentIdx=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipename = it.getString("NAME", "")

        }
        if(!recipename.isEmpty())
            editRecipe=loadRecipe(recipename)

        currentIdx=Utils.RecipeList.indexOf(recipename)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipeedit, menu)
    }

    fun popsaveDialog():Boolean
    {
        var foundDup=false;
        var currentRecipeName=""
        if(currentIdx>=0)
            currentRecipeName=Utils.RecipeList.get(currentIdx)

        alert {
            title = "Recipe Name"
            customView {

                verticalLayout {
                    val rn = editText(currentRecipeName) { }

                    positiveButton("Save") {


                            //edit recipe
                            //check for dup name
                            for(r:String in Utils.RecipeList)
                            {
                                //don't compare to itself
                                if(Utils.RecipeList.indexOf(r)==currentIdx)
                                    continue
                                if(r.equals(rn.text.toString()))
                                    foundDup=true;

                            }
                            if(!foundDup)
                                saveRecipe(rn.text.toString())
                            else
                            {
                                dupNameAlert()

                            }



                    }



                }
                cancelButton { }
            }

        }.show()

        return foundDup
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.updateRecipe -> {

                if(ingreds.size==0)
                {
                    alert("Your recipe has no ingredients!", "Wait!") {
                        cancelButton() {  } }.show()
                    return false
                }

                popsaveDialog()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun dupNameAlert() {
        alert("Duplicate Recipe Name!", "Oh no!") {
            positiveButton("Ok") {

                popsaveDialog()
            }
        }.show()
    }

    fun loadRecipe(name:String):Recipe
    {
        val file = File(Utils.getDataDir(context, "Recipes"), name)
        val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }
        val gson = Gson()
        val recipe:Recipe=gson.fromJson(inputAsString, Recipe::class.java)
            ingreds=recipe.ingredients

        return recipe
    }

    fun saveRecipe( name:String)
    {
        val recipe=Recipe(name, ingreds)

        val gson = Gson()
        val jsonString = gson.toJson(recipe)

        val file = File(Utils.getDataDir(context, "Recipes"), name)
        FileOutputStream(file).use {
            it.write(jsonString.toByteArray())
        }

        if(currentIdx>-1 )//for update check if the name changed and get rid of the json under the old name
        {
            val oldname=Utils.RecipeList.get(currentIdx)
             if(!oldname.equals(name)) {
                 //delete file for old name
                 val oldfile = File(Utils.getDataDir(context, "Recipes"), oldname)
                 oldfile.delete()
             }
        }

        //reload list
        Utils.loadRecipes(context)
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val fragment = recipeListFragment.newInstance()
        fragmentTransaction?.replace(R.id.recipefragment, fragment)
        fragmentTransaction?.commit()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val parentView=inflater.inflate(R.layout.fragment_recipe_edit, container, false)

        parentView.btnAdd.setOnClickListener{clickAdd()}
        parentView.edIngredient.requestFocus()

        lvAdapter = IngredAdapter(context, ingreds)
        parentView.lvIngreds.adapter = lvAdapter
        parentView.lvIngreds.onItemClickListener= AdapterView.OnItemClickListener { adapterView, view, position, id ->
            removeIngred(position)
        }

        return parentView
    }

    fun removeIngred(pos:Int)
    {
        alert("Remove ingredient?", "Wait!") {
            yesButton { try {

                ingreds.removeAt(pos)
                lvAdapter!!.notifyDataSetChanged()
            }
            catch( ex:Exception)
            {

            } }

            noButton { } }.show()

    }
    fun addIngred(ingred:String, cnt:Int)
    {
        try {
            //check for dups and just increment count for them
            var found=false
            for(i:Ingredient in ingreds)
            {
                if(i.item.equals(ingred))
                {
                    i.count++;
                    found=true
                break;
                }
            }
            if(!found)
                ingreds.add(Ingredient(ingred, cnt))

            lvAdapter!!.notifyDataSetChanged()
            edIngredient.text.clear()
            edAmount.text.clear()

        }
        catch( ex:Exception)
        {

        }
    }
    fun clickAdd()
    {

        //check atleast 1 ingredient
        if(edIngredient.text.isEmpty()) {
            alert("Add an ingredient!", "Wait!") {
                cancelButton { } }.show()

            edIngredient.requestFocus()
            return
        }
        var cnt="1"
        if(!edAmount.text.isEmpty())
            cnt=edAmount.text.toString()
        addIngred(edIngredient.text.toString(), (cnt).toInt())


    }
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
           // throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String) =
                recipeEdit().apply {
                    arguments = Bundle().apply {
                        putString("NAME", name)

                    }
                }
    }

    inner class IngredAdapter : BaseAdapter {

        private var ingredList = arrayListOf<Ingredient>()
        private var context: Context? = null

        constructor(context: Context?, notesList:ArrayList<Ingredient>) : super() {
            this.ingredList = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.ingredient_item, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
              //  Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
            if(ingredList.get(position).count>1)
                vh.txItem.text=ingredList.get(position).count.toString()+" "+ingredList.get(position).item
            else
                vh.txItem.text=ingredList.get(position).item

            return view
        }

        override fun getItem(position: Int): Any {
            return ingredList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return ingredList.size
        }
    }

    inner class ViewHolder(view: View?) {
        val btnRemove: ImageView
        val txItem: TextView

        init {
            this.btnRemove = view?.findViewById(R.id.btnRemove) as ImageView
            this.txItem = view.findViewById(R.id.txItem) as TextView
        }

    }
}
