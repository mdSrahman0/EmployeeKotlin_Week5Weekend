package com.example.employeekotlin_week5weekend.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.employeekotlin_week5weekend.R
import com.example.employeekotlin_week5weekend.database.DatabaseContract
import kotlinx.android.synthetic.main.activity_filter_employee.*

class FilterEmployeeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var spinner : Spinner? = null
    var departmentList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_employee)

        spinner = this.departmentSpinner
        spinner!!.setOnItemSelectedListener(this)

        departmentList = DatabaseContract.EmployeeEntry.departmentList

        // Add this text as the first item in our spinner.
        departmentList.add(0,"SELECT DEPARTMENT")

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, departmentList)
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = myAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var selected = departmentList[position]
        // the first position just holds the text "SELECT DEPARTMENT." We don't want anything to happen in this position
        if (position == 0) {
        }
        // an actual department was selected
        else {
            val intent = Intent(this, EmployeeListActivity::class.java)
            intent.putExtra("selected department", selected)
            startActivity(intent)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
