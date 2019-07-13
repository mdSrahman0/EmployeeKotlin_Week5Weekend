package com.example.employeekotlin_week5weekend.activities

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View.*
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.employeekotlin_week5weekend.R
import com.example.employeekotlin_week5weekend.database.DatabaseHelper
import com.example.employeekotlin_week5weekend.fragments.DeleteFragment
import com.example.employeekotlin_week5weekend.fragments.DetailsFragment
import com.example.employeekotlin_week5weekend.fragments.NewEmployeeFragment
import com.example.employeekotlin_week5weekend.fragments.UpdateFragment
import com.example.employeekotlin_week5weekend.pojo.Employee
import kotlinx.android.synthetic.main.content_employee_list.*

class EmployeeListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
                            NewEmployeeFragment.OnFragmentInteractionListener,
                            UpdateFragment.OnFragmentInteractionListener,
                            DeleteFragment.OnDeleteFragmentInteractionListener,
                            DetailsFragment.OnDetailsFragmentInteractionListener{

    var employeeNames = ArrayList<Employee>()
    private var dbHelper : DatabaseHelper =
        DatabaseHelper(this)
    val fragmentManager = supportFragmentManager
    var selectedDepartment : String = ""

    override fun onNewEmployeeFragmentInteraction() {
        fragmentManager.popBackStack()
        val t = Thread(runnable())
        t.start()
    }

    override fun onUpdateFragmentInteraction() {
        fragmentManager.popBackStack()
        val t = Thread(runnable())
        t.start()
    }

    override fun onDeleteFragmentInteraction() {
        fragmentManager.popBackStack()
        val t = Thread(runnable())
        t.start()
    }

    override fun onDetailsFragmentInteraction() {
        fragmentManager.popBackStack()
        val t = Thread(runnable())
        t.start()
    }

    // get all the employees in the selected department
    fun runnable(): Runnable {
        return Runnable {
            try {
                // first clear the employee names array, then get all employees from the selected database
                employeeNames.clear()
                employeeNames = dbHelper.getAllEmployeesByDepartment(selectedDepartment)

                // needed for arrayadapter
                val namesArray = arrayOfNulls<String>(employeeNames.size)

                // retrieve first and last name of employee and store into each array index
                for (i in employeeNames.indices) {
                    namesArray[i] = employeeNames.get(i).firstName + " " + employeeNames.get(i).lastName
                }

                // updates to UI elements must be done on UI thread
                runOnUiThread(Runnable {
                    val adapter = ArrayAdapter(this, R.layout.listview_item, namesArray)
                    val listView: ListView = findViewById(R.id.listView)
                    listView.adapter = adapter
                    tvEmployeeListInfo.visibility = VISIBLE
                    listView.visibility = VISIBLE
                })
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        selectedDepartment = intent.getStringExtra("selected department")

        Log.d("TAG", selectedDepartment)

        tvEmployeeListInfo.setText("Employees in $selectedDepartment")

        employeeNames = dbHelper.getAllEmployeesByDepartment(selectedDepartment)

        // new array to hold the first and last name of each returned employee
        val namesArray = arrayOfNulls<String>(employeeNames.size)

        // retrieve first and last name of employee and store into each array index
        for (i in employeeNames.indices) {
            namesArray[i] = employeeNames.get(i).firstName + " " + employeeNames.get(i).lastName
        }

        val adapter = ArrayAdapter(this, R.layout.listview_item, namesArray)
        val listView:ListView = findViewById(R.id.listView)
        listView.adapter = adapter

        ///////////////// Provided code for toolbar /////////////////
        //-------------------------------------------------------------
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_new_employee -> {
                val newEmployeeFragment = NewEmployeeFragment()
                listView.visibility = GONE
                tvEmployeeListInfo.visibility = GONE
                fragmentManager?.beginTransaction()?.add(R.id.frgPlaceHolder, newEmployeeFragment)?.addToBackStack("FRG")?.commit()
            }
            R.id.nav_update_employee -> {
                val updateFragment = UpdateFragment()
                listView.visibility = GONE
                tvEmployeeListInfo.visibility = GONE
                fragmentManager?.beginTransaction()?.add(R.id.frgPlaceHolder, updateFragment)?.addToBackStack("FRG")?.commit()
            }
            R.id.nav_delete_employee -> {
                val deleteFragment = DeleteFragment()
                listView.visibility = GONE
                tvEmployeeListInfo.visibility = GONE
                fragmentManager?.beginTransaction()?.add(R.id.frgPlaceHolder, deleteFragment)?.addToBackStack("FRG")?.commit()
            }
            R.id.nav_detail_employee -> {
                val detailsFragment = DetailsFragment()
                listView.visibility = GONE
                tvEmployeeListInfo.visibility = GONE
                fragmentManager?.beginTransaction()?.add(R.id.frgPlaceHolder, detailsFragment)?.addToBackStack("FRG")?.commit()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.employee_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
