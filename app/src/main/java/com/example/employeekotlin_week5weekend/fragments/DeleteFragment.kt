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
import com.example.employeekotlin_week5weekend.R
import kotlinx.android.synthetic.main.fragment_delete.*
import kotlinx.android.synthetic.main.fragment_new_employee.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DeleteFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnDeleteFragmentInteractionListener? = null
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
        return inflater.inflate(R.layout.fragment_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDelete.setOnClickListener{
            val taxId = view.findViewById<EditText>(R.id.etTaxID)?.text?.trim().toString()
            val t = Thread(runnable(taxId))
            t.start()
        }
    }

    fun runnable(taxId: String): Runnable {
        Log.d("TAG", "inside DeleteFragment runnable.")
        return Runnable {
            try {
                Log.d("TAG", "inside UpdateFragment runnable - try block.")
                dbHelper?.deleteSingleEmployee(taxId)
                listener?.onDeleteFragmentInteraction()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHelper = DatabaseHelper(activity)
        if (context is OnDeleteFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnDeleteFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onDeleteFragmentInteraction()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeleteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
