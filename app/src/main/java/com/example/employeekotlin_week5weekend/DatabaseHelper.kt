package com.example.employeekotlin_week5weekend

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_TAX_ID
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_FNAME
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_LNAME
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_STR_ADDRESS
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_CITY
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_STATE
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_ZIP
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_POSITION
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.FIELD_DEPARTMENT
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.TABLE_NAME
import com.example.employeekotlin_week5weekend.DatabaseContract.EmployeeEntry.Companion.whereClauseForUpdate

class DatabaseHelper(context: Context?, name: String?, version: Int, openParams: SQLiteDatabase.OpenParams) :
    SQLiteOpenHelper(context, name, version, openParams) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    // ONLY CALLED WHEN CHANGING THE STRUCTURE OF A DATABASE (like adding another column field),
    // NOT WHEN YOU UPDATE A DATABASE (like adding another entry).
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    // INSERT SINGLE EMPLOYEE
    @Throws(SQLiteConstraintException::class)
    fun insertEmployee(employee : Employee): Boolean {
        val db = writableDatabase

        val contentValues = ContentValues()
        contentValues.put(FIELD_TAX_ID, employee.taxId)
        contentValues.put(FIELD_FNAME, employee.firstName)
        contentValues.put(FIELD_LNAME, employee.lastName)
        contentValues.put(FIELD_STR_ADDRESS,employee.address)
        contentValues.put(FIELD_CITY, employee.city)
        contentValues.put(FIELD_STATE, employee.state)
        contentValues.put(FIELD_ZIP, employee.zip)
        contentValues.put(FIELD_POSITION, employee.position)
        contentValues.put(FIELD_DEPARTMENT, employee.department)

        val newRowId = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return true
    }

    // UPDATE SINGLE EMPLOYEE
    fun updateSingleEmployee(employee : Employee) {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(FIELD_TAX_ID, employee.taxId)
        contentValues.put(FIELD_FNAME, employee.firstName)
        contentValues.put(FIELD_LNAME, employee.lastName)
        contentValues.put(FIELD_STR_ADDRESS, employee.address)
        contentValues.put(FIELD_CITY, employee.city)
        contentValues.put(FIELD_STATE, employee.state)
        contentValues.put(FIELD_ZIP, employee.zip)
        contentValues.put(FIELD_POSITION, employee.position)
        contentValues.put(FIELD_DEPARTMENT, employee.department)

        writableDatabase.update(TABLE_NAME, contentValues, whereClauseForUpdate(employee.taxId), null)
        writableDatabase.close()
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteSingleEmployee(taxId : String) : Boolean {
        val db = writableDatabase

        // Define the "where" part of the query
        val selection = DatabaseContract.EmployeeEntry.FIELD_TAX_ID + " LIKE ?"

        // return all elements with the taxID
        val selectionArgs = arrayOf(taxId)
        db.delete(DatabaseContract.EmployeeEntry.TABLE_NAME, selection, selectionArgs)
        return true
    }

    // return a single employee
    fun getSingleEmployeeByTaxID(taxId : String) : Employee {
        val db = readableDatabase

        lateinit var returnEmployee : Employee
        var cursor = db.rawQuery(DatabaseContract.EmployeeEntry.getEmployeeByTaxID(taxId), null)
        if(cursor.moveToFirst()) {
            var taxId = cursor.getString(cursor.getColumnIndex(FIELD_TAX_ID))
            var fName = cursor.getString(cursor.getColumnIndex(FIELD_FNAME))
            var lName = cursor.getString(cursor.getColumnIndex(FIELD_LNAME))
            var strAddr = cursor.getString(cursor.getColumnIndex(FIELD_STR_ADDRESS))
            var city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
            var state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
            var zip = cursor.getString(cursor.getColumnIndex(FIELD_ZIP))
            var position = cursor.getString(cursor.getColumnIndex(FIELD_POSITION))
            var department = cursor.getString(cursor.getColumnIndex(FIELD_DEPARTMENT))
            returnEmployee = Employee(taxId, fName, lName, strAddr, city, state, zip, position, department)
        }
        cursor.close()
        db.close()
        return returnEmployee
    }

    // return ALL employees
    fun getAllEmployeesByDepartment(desiredDepartment : String): ArrayList<Employee> {
        val employeeList = ArrayList<Employee>()
        val db = writableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery(DatabaseContract.EmployeeEntry.getEmployeesByDepartment(desiredDepartment),null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList() // return empty arraylist if rawQuery to find table failed
        }

        if(cursor.moveToFirst()) {
            do{
                var taxId = cursor.getString(cursor.getColumnIndex(FIELD_TAX_ID))
                var fName = cursor.getString(cursor.getColumnIndex(FIELD_FNAME))
                var lName = cursor.getString(cursor.getColumnIndex(FIELD_LNAME))
                var strAddr = cursor.getString(cursor.getColumnIndex(FIELD_STR_ADDRESS))
                var city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                var state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                var zip = cursor.getString(cursor.getColumnIndex(FIELD_ZIP))
                var position = cursor.getString(cursor.getColumnIndex(FIELD_POSITION))
                var department = cursor.getString(cursor.getColumnIndex(FIELD_DEPARTMENT))
                employeeList.add(Employee(taxId, fName, lName, strAddr, city, state, zip, position, department))
            }while(cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return employeeList
    }

    companion object {
        private val SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_TAX_ID + " TEXT PRIMARY KEY," +
                FIELD_FNAME + " TEXT," +
                FIELD_LNAME + " TEXT," +
                FIELD_STR_ADDRESS + " TEXT," +
                FIELD_CITY + " TEXT," +
                FIELD_STATE + " TEXT," +
                FIELD_ZIP + " TEXT," +
                FIELD_DEPARTMENT + " TEXT," +
                FIELD_POSITION + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
    }
}