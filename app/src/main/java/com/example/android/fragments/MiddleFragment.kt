package com.example.android.fragments

import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.size
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//import edu.umb.cs.preferencedatastore.R

class MiddleFragment : Fragment(R.layout.field_and_button_layout) {
    var mCurrentPosition = -1
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState != null) {
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
    fun updateArticleView(position: Int) {
        (activity as MainActivity).onArticleSelected(9)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val showTask = activity?.findViewById<Button>(R.id.showTask)
        var table = activity?.findViewById<TableLayout>(R.id.table)
        var row = TableRow(activity)

      //  if(mCurrentPosition>=0) updateArticleView(mCurrentPosition)
   if(        mCurrentPosition == -1){
       mCurrentPosition++
        showTask?.setOnClickListener{
           updateArticleView(9)
            table?.removeAllViews()
            table?.addView (row )
        }
   }

//updateArticleView(5)

        val buttonChangeFrag = activity?.findViewById<Button>(R.id.changeFragButton)
        buttonChangeFrag?.setOnClickListener{
            (activity as MainActivity).onArticleSelected(2)
        }

}
    private fun addit(row: TableRow) {
        var tablem = activity?.findViewById<TableLayout>(R.id.table)
      //  tablem?.addView(row)
    }
}


