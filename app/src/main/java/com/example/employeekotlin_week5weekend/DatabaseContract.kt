package com.example.employeekotlin_week5weekend

import android.provider.BaseColumns
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList

object DatabaseContract {

    class EmployeeEntry : BaseColumns {
        companion object {
            val DATABASE_NAME = "db_name"
            val db_version = 1;
            val TABLE_NAME = "employee_table"
            val FIELD_TAX_ID = "taxID"
            val FIELD_FNAME = "first name"
            val FIELD_LNAME = "last name"
            val FIELD_STR_ADDRESS = "street address"
            val FIELD_CITY = "city"
            val FIELD_STATE = "state"
            val FIELD_ZIP = "zip"
            val FIELD_POSITION = "position"
            val FIELD_DEPARTMENT = "department"

            val departmentList = arrayListOf("Android", "iOS", "Windows")

            // retrieve all employees in a particular department
            fun getEmployeesByDepartment(passedInDepartment: String) : String {
                return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_DEPARTMENT, passedInDepartment)
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
                return String.format(Locale.US, "WHERE %s = %s", FIELD_TAX_ID)
            }
        } // end companion object
    } // end class
} // end object

//    fun getCreateTableStatement() : String {
//        return String.format(Locale.US, "CREATE TABLE %s(%s TEXT PRIMARY_KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, " +
//                "%s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, FIELD_TAX_ID, FIELD_FNAME, FIELD_LNAME, FIELD_STR_ADDRESS, FIELD_CITY,
//                                FIELD_STATE, FIELD_ZIP, FIELD_POSITION, FIELD_DEPARTMENT)
//    }
//
//    // retrieve all employees
//    fun getAllEmployees() : String{
//        return String.format(Locale.US, "SELECT * FROM %s", TABLE_NAME)
//    }
//
//    // update an employee
//    fun updateEmployee(passedInFName: String) {
//
//    // filter employees by tax_id
//    fun getEmployeeByTaxID(passedInTaxId : String) : String {
//        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_TAX_ID, passedInTaxId)

//
//    // by first name
//    fun getEmployeeByFName(passedInFName : String) : String {
//        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_FNAME, passedInFName)
//    }
//
//    // by last name
//    fun getEmployeeByLName(passedInLName : String) : String {
//        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_LNAME, passedInLName)
//    }
//
//    // by street address
//    fun getEmployeeByStrAddr(passedInStrAddr : String) : String {
//        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_STR_ADDRESS, passedInStrAddr)
//    }
//
//    // by city
//    fun getEmployeeByCity(passedInCity : String) : String {
//        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_CITY, passedInCity)
//    }
//
//    // by state
//    fun getEmployeeByState(passedInState : String) : String {
//        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_STATE, passedInState)
//    }
//
//    // by zip
//    fun getEmployeeByZip(passedInZip : String) : String {
//        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_ZIP, passedInZip)
//    }
//
//    // by position
//    fun getEmployeeByPosition(passedInPosition: String) : String {
//        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"s\"", TABLE_NAME, FIELD_POSITION, passedInPosition)
//    }
//
//    // by department
//    fun getEmployeeByDepartment() : String {
//        return String.format(Locale.US, "SELECT DISTINCT %s FROM %s", FIELD_DEPARTMENT, TABLE_NAME)
//    }
