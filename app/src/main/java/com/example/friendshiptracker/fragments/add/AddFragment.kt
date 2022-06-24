package com.example.friendshiptracker.fragments.add

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.data.User
import com.example.notesapp.data.UserViewModel
import java.util.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        val btn = view.findViewById<Button>(R.id.add_submit)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        btn.setOnClickListener {
            insertDataToDatabse(view)
        }


        val When = view.findViewById<TextView>(R.id.add_When)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        When.setOnClickListener {

            Toast.makeText(requireContext(), "data added to database", Toast.LENGTH_SHORT).show()

            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{ view, myear, mmonth, mday ->
                When.text = "" + mday + "/" + mmonth +"/"+myear
            },year,month,day)
            dpd.show()
        }
        return view
    }

    private fun insertDataToDatabse(view: View) {
        val firstname_et = view.findViewById<EditText>(R.id.add_firstname)
        val firstname = firstname_et.text.toString()
        val where_et = view.findViewById<EditText>(R.id.add_where)
        val where = where_et.text.toString()
        val When = view.findViewById<TextView>(R.id.add_When)
        // When.text = args.currentUser.When
        val why_et = view.findViewById<EditText>(R.id.add_why)
        val why = why_et.text.toString()
        val user = User(0, firstname, where, When.text.toString(),why )
        mUserViewModel.addUser(user)
        Toast.makeText(requireContext(), "data added to database", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }


}
