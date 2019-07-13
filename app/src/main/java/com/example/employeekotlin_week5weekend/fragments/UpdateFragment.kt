package com.example.employeekotlin_week5weekend.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.employeekotlin_week5weekend.database.DatabaseHelper
import com.example.employeekotlin_week5weekend.pojo.Employee
import com.example.employeekotlin_week5weekend.R
import kotlinx.android.synthetic.main.fragment_new_employee.*
import kotlinx.android.synthetic.main.fragment_update.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UpdateFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var dbHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            btnUpdateEmployee.setOnClickListener {
                val firstName = view.findViewById<EditText>(R.id.etfName)?.text?.trim().toString()
                val lastName = view.findViewById<EditText>(R.id.etLName)?.text?.trim().toString()
                val strAddr = view.findViewById<EditText>(R.id.etStrAddr)?.text?.trim().toString()
                val city = view.findViewById<EditText>(R.id.etCity)?.text?.trim().toString()
                val state = view.findViewById<EditText>(R.id.etState)?.text?.trim().toString()
                val zip = view.findViewById<EditText>(R.id.etZip)?.text?.trim().toString()
                val taxId = view.findViewById<EditText>(R.id.etTaxID)?.text?.trim().toString()
                val position = view.findViewById<EditText>(R.id.etPosition)?.text?.trim().toString()
                val department = view.findViewById<EditText>(R.id.etDepartment)?.text?.trim().toString()

                val employee = Employee(
                    firstName,
                    lastName,
                    strAddr,
                    city,
                    state,
                    zip,
                    taxId,
                    position,
                    department
                )
                val t = Thread(runnable(employee))
                t.start()
            }
        }

    fun runnable(employee: Employee): Runnable {
        Log.d("TAG", "inside UpdateFragment runnable.")
        return Runnable {
            try {
                Log.d("TAG", "inside UpdateFragment runnable - try block.")
                dbHelper?.updateSingleEmployee(employee)
                listener?.onUpdateFragmentInteraction()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHelper = DatabaseHelper(activity)
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

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onUpdateFragmentInteraction()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpdateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
