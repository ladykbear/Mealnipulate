package com.bonehill.mealnipulate

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import kotlinx.android.synthetic.main.fragment_recipe_list.view.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton
import java.io.File
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [recipeListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [recipeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class recipeListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var lvAdapter: RecipesAdapter?=null;
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val parentView= inflater.inflate(R.layout.fragment_recipe_list, container, false)
        lvAdapter = RecipesAdapter(context)
        parentView.lvRecipes.adapter = lvAdapter
        parentView.lvRecipes.onItemClickListener= AdapterView.OnItemClickListener { adapterView, view, position, id ->
           // removeRecipe(position)

            val parent = activity as recipeActivity?
            parent?.EditRecipe(Utils.RecipeList.get(position))
        }


        return parentView

    }
    fun removeRecipe(pos:Int)
    {
        alert("Delete Recipe?", "Wait!") {
            yesButton { try {

                val file = File(Utils.getDataDir(context, "Recipes"), Utils.RecipeList.get(pos))
                file.delete()

                Utils.loadRecipes(context)
                if(Utils.RecipeList.count()==0)
                    startActivity<MainActivity>()

                lvAdapter!!.notifyDataSetChanged()
            }
            catch( ex: Exception)
            {

            } }

            noButton { } }.show()

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
          //  throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    inner class RecipesAdapter : BaseAdapter {

        private var context: Context? = null

        constructor(context: Context?) : super() {

            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.ingredient_item, parent, false)
                vh =ViewHolder(view)
                view.tag = vh


            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
                vh.txItem.text=Utils.RecipeList.get(position)
             vh.btnRemove.setOnClickListener {
                 removeRecipe(position)
             }



            return view
        }

        override fun getItem(position: Int): Any {
            return Utils.RecipeList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return Utils.RecipeList.size
        }
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
    private class ViewHolder(view: View?) {
        val btnRemove: ImageView
        val txItem: TextView

        init {
            this.btnRemove = view?.findViewById(R.id.btnRemove) as ImageView
            this.txItem = view?.findViewById(R.id.txItem) as TextView
        }

    }
    companion object {

        @JvmStatic
        fun newInstance() =
                recipeListFragment().apply {

                }
    }
}
