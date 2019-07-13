package com.example.employeekotlin_week5weekend.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.employeekotlin_week5weekend.R
import com.example.employeekotlin_week5weekend.database.DatabaseHelper
import kotlinx.android.synthetic.main.fragment_details.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnDetailsFragmentInteractionListener? = null
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
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSeeDetails.setOnClickListener {
            val taxId = view.findViewById<EditText>(R.id.etEnterTaxID)?.text?.trim().toString()
            val t = Thread(runnable(taxId))
            t.start()
        }
    }

    fun runnable(taxId: String): Runnable {
        return Runnable {
            try {
                val employee = dbHelper?.getSingleEmployeeByTaxID(taxId)
                tvFName.text = employee?.firstName
                tvLName.text = employee?.lastName
                tvStrAddr.text = employee?.address
                tvCity.text = employee?.city
                tvState.text = employee?.state
                tvZip.text = employee?.zip
                tvTaxID.text = employee?.taxId
                tvPosition.text = employee?.position
                tvDepartment.text = employee?.department
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onDetailsFragmentInteraction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHelper = DatabaseHelper(activity)
        if (context is OnDetailsFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnDetailsFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onDetailsFragmentInteraction()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
