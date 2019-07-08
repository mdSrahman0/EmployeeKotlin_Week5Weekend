package com.example.employeekotlin_week5weekend

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_new_employee.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewEmployeeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var dbHelper : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d("TAG", "NewEmployeeFragment onCreateView")
        val view = inflater.inflate(R.layout.fragment_new_employee, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG", "Inside NewEmployeeFragment onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        btnSendNewEmployee.setOnClickListener {
            var firstName = view?.findViewById<EditText>(R.id.etfName)?.text.toString()
            var lastName = view?.findViewById<EditText>(R.id.etLName)?.text.toString()
            var strAddr = view?.findViewById<EditText>(R.id.etStrAddr)?.text.toString()
            var city = view?.findViewById<EditText>(R.id.etCity)?.text.toString()
            var state = view?.findViewById<EditText>(R.id.etState)?.text.toString()
            var zip = view?.findViewById<EditText>(R.id.etZip)?.text.toString()
            var taxId = view?.findViewById<EditText>(R.id.etTaxID)?.text.toString()
            var position = view?.findViewById<EditText>(R.id.etPosition)?.text.toString()
            var department = view?.findViewById<EditText>(R.id.etPosition)?.text.toString()
             //pass info in as a new employee. in the interface, pass that employee back to the
             //calling employeelist activity and update the list
            val employee = Employee(firstName, lastName, strAddr, city, state, zip, taxId, position, department)

            //dbHelper = DatabaseHelper(this)
            Log.d("TAG", employee.firstName)

            val t = Thread(runnable(employee))
            t.start()
        }
    }

    fun runnable(employee: Employee): Runnable {
        Log.d("TAG", "inside NewEmployeeFragment runnable.")

        return Runnable {
            try {
                Log.d("TAG", "inside NewEmployeeFragment runnable - try block.")
                dbHelper.insertEmployee(employee)
                Log.d("TAG", "${dbHelper.getAllEmployeesByDepartment("Android")}")
                //listener?.onFragmentInteraction(employee)
                Thread.sleep(4000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(employee : Employee)
    }

    fun onButtonPressed(employee: Employee) {
        listener?.onFragmentInteraction(employee)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewEmployeeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    Log.d("TAG", "NewEmployeeFragment newInstance()")
                }
            }
    }
}
