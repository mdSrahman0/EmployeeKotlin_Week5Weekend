/**
 *
1. Create an object Employee. The class should have the following member variables:
        1. FirstName
        2. LastName
        3. StreetAddress
        4. City
        5. State
        6. Zip
        7. TaxID
        8. Position
        9. Department
2. Create a database (Room or Sqlite) for the Employee object.
1. MainActivity
    1. Make this into a splash screen activity.
    2. After any initializations for the applications, start the FilterEmployeeActivity.
2. FilterEmployeeActivity
    1. Be creative, but must at least use one spinner to select department of the employee. DEPARTMENTS MUST BE RETRIEVED FROM DATABASE.
    2. This activity will start the EmployeeListActivity.
3. EmployeeListActivity
    1. List all the employees matching the criteria selected in the FilterEmployeeActivity.
    2. Implement a Navigation drawer here for the following activities. Since this activity will also be called from the
    Employee details fragment, you will need to devise a way to get the info for a specific employee both without the employee
    known and with the employee known.
4. NewEmployeeFragment
    1. Allows the user to enter a new employee into the database.
    2. Once operation is complete, go back to the Listing Activity.
    3. When insert is complete, display a toast letting user know which employee was inserted
5. DeleteEmployeeFragment
    1. Allow user to delete an employee from the database.
    2. Once operation is complete, go back to the Listing activity.
    3. When delete is complete, display a toast letting user know which employee was deleted
6. UpdateEmployeeFragment
    1. Allow user to update an employee from the database.
    2. Once operation is complete, go back to the Listing activity.
    3. When update is complete, display a toast letting user know which employee was updated
7. EmployeeDetailsFragment
    1. Display all info about the employee.
    2. Be able to pass the employee to the following activities:
                1. DeleteEmployeeFragment
                2. UpdateEmployeeFragment
4. ALL DATABASE OPERATIONS MUST BE HANDLED BY A WORKER THREAD. You can use any scheme we have covered or one of the following:
    1. Loopers
    2. Loaders
    3. ThreadPools
5. All activities/fragments except Main, filter, and list activities must have backward support. ]
(The back arrow in the left side of action bar)
6. Any activity that requires user input must account for configurational changes in some way.
7. Feel free to experiment with any UI element
 */

package com.example.employeekotlin_week5weekend.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.employeekotlin_week5weekend.R
import com.example.employeekotlin_week5weekend.database.DatabaseHelper
import com.example.employeekotlin_week5weekend.pojo.Employee
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dbHelper : DatabaseHelper =
        DatabaseHelper(this)
    var employeeNames = ArrayList<Employee>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         //FOR TESTING DATABASE RETRIEVAL

        var employee2 = Employee("Md", "Rahman", "5656 Ming Dr.", "Englewood",
            "CO", "80224", "1", "developer","Android")
        var employee3 = Employee("Miley", "Ras", "4164 W. St", "Centinneal",
            "AK", "80111", "2", "human resources","iOS")
        dbHelper.insertEmployee(employee2)
        dbHelper.insertEmployee(employee3)
        var employee4 = Employee("Janet", "SnakeHole", "7744 W. Ivy St", "Cinncinati",
            "OH", "80445", "3", "human resources","iOS")
        var employee5 = Employee("Michael", "Jackson", "5678 E. Jack St", "Atlanta",
            "GA", "30089", "4", "developer", "Windows");
        dbHelper.insertEmployee(employee4)
        dbHelper.insertEmployee(employee5)
    }

    fun onClick(view : View) {
        val intent = Intent(this, FilterEmployeeActivity::class.java)
        startActivity(intent)
    }
}
