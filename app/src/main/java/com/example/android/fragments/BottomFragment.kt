package com.example.android.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//import edu.umb.cs.preferencedatastore.R

class BottomFragment : Fragment(R.layout.textview_layout) {
    var mCurrentPosition = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      var table = activity?.findViewById<TableLayout>(R.id.table)
      var row = TableRow(activity)
      var txt = TextView(activity)
      txt.setText("ss")
      row.addView(txt)
      table?.addView(row)
       // if(mCurrentPosition>=0) updateArticleView(mCurrentPosition)

        val buttonChangeFrag = activity?.findViewById<Button>(R.id.changeFragButton1)
        buttonChangeFrag?.setOnClickListener{
            (activity as MainActivity).onArticleSelected(1)

}
        val back = activity?.findViewById<Button>(R.id.backbtn)

        back?.setOnClickListener{
            (activity as MainActivity).onArticleSelected(7)

        }
    }}