package com.example.employeekotlin_week5weekend

import android.net.Uri
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Layout
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.content_employee_list.*

class EmployeeListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
                                NewEmployeeFragment.OnFragmentInteractionListener {

    var employeeNames = ArrayList<Employee>()
    val db : DatabaseHelper? = null
    val fragmentManager = supportFragmentManager
    var selectedDepartment : String = ""

    override fun onFragmentInteraction(employee: Employee) {
        //val t = Thread(runnable())
        Log.d("TAG", "Inside EmployeeListActivity. Received:  ${employee.firstName}")
        Log.d("TAG", "Inside EmployeeListActivity. Department:  $selectedDepartment")
        fragmentManager.popBackStack()
        //t.start()
    }

    // get all the employees in the selected department
    fun runnable(): Runnable {
        return Runnable {
            try {
                //employeeNames = db!!.getAllEmployeesByDepartment(selectedDepartment)

                Log.d("TAG", "inside EmployeeListActivity runnable. Selected Department is $selectedDepartment")
                val namesArray = arrayOfNulls<String>(employeeNames.size)

                // retreive first and last name of employee and store into each array index
                for (i in employeeNames.indices) {
                    namesArray[i] = employeeNames.get(i).firstName + " " + employeeNames.get(i).lastName
                }

                //listview_item has a single textview that will display each individual item
                val adapter = ArrayAdapter(this, R.layout.listview_item, namesArray)

                val listView:ListView = findViewById(R.id.listView)
                listView.adapter = adapter
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

        // new array to hold the first and last name of each returned employee
        val namesArray = arrayOfNulls<String>(employeeNames.size)

        // retreive first and last name of employee and store into each array index
        for (i in employeeNames.indices) {
            namesArray[i] = employeeNames.get(i).firstName + " " + employeeNames.get(i).lastName
        }
        //listview_item has a single textview that will display each individual item
        val adapter = ArrayAdapter(this, R.layout.listview_item, namesArray)

        val listView:ListView = findViewById(R.id.listView)
        listView.adapter = adapter


        // Provided code for toolbar /////////////////
        //-------------------------------------------------------------
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_new_employee -> {
                //findViewById<Layout>(R.id.)
                Log.d("TAG", "new employee clicked")
                val newEmployeeFragment = NewEmployeeFragment()
                fragmentManager?.beginTransaction()?.add(R.id.frgNewEmployeePlaceHolder, newEmployeeFragment)
                            ?.addToBackStack("FRG_NEW_EMP")?.commit()
            }
            R.id.nav_update_employee -> {

            }
            R.id.nav_delete_employee -> {

            }
            R.id.nav_detail_employee -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
