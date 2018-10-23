package com.bonehill.mealnipulate

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

class mealplanfrag : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mealplanfrag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinMon: Spinner = view!!.findViewById(R.id.spinMon)
        ArrayAdapter.createFromResource(
                view!!.context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinMon.adapter = adapter
        }
        val spinTues: Spinner = view!!.findViewById(R.id.spinMon)
        ArrayAdapter.createFromResource(
                view!!.context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinTues.adapter = adapter
        }
        val spinWed: Spinner = view!!.findViewById(R.id.spinMon)
        ArrayAdapter.createFromResource(
                view!!.context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinWed.adapter = adapter
        }
        val spinThurs: Spinner = view!!.findViewById(R.id.spinMon)
        ArrayAdapter.createFromResource(
                view!!.context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinThurs.adapter = adapter
        }
        val spinFri: Spinner = view!!.findViewById(R.id.spinMon)
        ArrayAdapter.createFromResource(
                view!!.context,
                R.array.defaultRecipe,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinFri.adapter = adapter
        }
    }

    companion object {
        fun newInstance(): mealplanfrag = mealplanfrag()
    }
}
