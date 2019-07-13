package com.example.employeekotlin_week5weekend.database

import android.provider.BaseColumns
import java.util.*

object DatabaseContract {

    class EmployeeEntry : BaseColumns {
        companion object {
            val DATABASE_NAME = "db_name"
            val DATABASE_VERSION = 2;
            val TABLE_NAME = "employee_table"
            val FIELD_TAX_ID = "taxID"
            val FIELD_FNAME = "first_name"
            val FIELD_LNAME = "last_name"
            val FIELD_STR_ADDRESS = "street_address"
            val FIELD_CITY = "city"
            val FIELD_STATE = "state"
            val FIELD_ZIP = "zip"
            val FIELD_POSITION = "position"
            val FIELD_DEPARTMENT = "department"

            val departmentList = arrayListOf("Android", "iOS", "Windows")

            // retrieve all employees in a particular department
            fun getEmployeesByDepartment(passedInDepartment: String) : String {
                return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"%s\"",
                    TABLE_NAME,
                    FIELD_DEPARTMENT, passedInDepartment)
            }

            // retrieve single employee be Tax ID
            fun getEmployeeByTaxID(passedInTaxId: String): String {
                return String.format(
                    Locale.US,
                    "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_TAX_ID, passedInTaxId
                )
            }


            // update single employee given their taxID
            fun whereClauseForUpdate(taxID: String): String {
                return String.format(Locale.US, "WHERE %s = %s",
                    FIELD_TAX_ID, taxID
                )
            }
        } // end companion object
    } // end class
} // end object
