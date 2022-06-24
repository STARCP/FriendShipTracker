package com.example.friendshiptracker.fragments.update

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.User
import com.example.notesapp.data.UserViewModel
import java.util.*


class update : Fragment() {

    private val args by navArgs<updateArgs>()

    private lateinit var mUserViewModel: UserViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val firstname = view.findViewById<EditText>(R.id.update_firstname)
        val name = args.currentUser.firstName
        firstname.setText(name)

        val where = view.findViewById<EditText>(R.id.update_where)
        where.setText(args.currentUser.Where)

        val When = view.findViewById<TextView>(R.id.update_When)
        When.text = args.currentUser.When

        val why = view.findViewById<EditText>(R.id.update_why)
        why.setText(args.currentUser.Why)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        When.setOnClickListener {

            //Toast.makeText(requireContext(), "data added to database", Toast.LENGTH_SHORT).show()

            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{ view, myear, mmonth, mday ->
                When.text = "" + mday + "/" + mmonth +"/"+myear
            },year,month,day)
            dpd.show()
        }



        val btn = view.findViewById<Button>(R.id.update_update)
        btn.setOnClickListener {

            val user = User(args.currentUser.id, firstname.text.toString(), where.text.toString(), When.text.toString(),why.text.toString() )
            Toast.makeText(requireContext(), user.Why, Toast.LENGTH_SHORT).show()

            mUserViewModel.updateUser(user)

            findNavController().navigate(R.id.action_update_to_listFragment)
        }


        setHasOptionsMenu(true)


        return view
    }
    private fun updateItem(view: View) {
        val firstname = view.findViewById<EditText>(R.id.update_firstname).text.toString()
        val where = view.findViewById<EditText>(R.id.update_where).text.toString()
        val When = view.findViewById<EditText>(R.id.update_When).text.toString()
        val why = view.findViewById<EditText>(R.id.update_why).text.toString()

        val updatedUser = User(args.currentUser.id, firstname, where, When, why)
        mUserViewModel.updateUser(updatedUser)
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_menu)
        {
            deleteUser(view)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(view: View?) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES") { _, _  ->

            mUserViewModel.deleteUser(args.currentUser)

            findNavController().navigate(R.id.action_update_to_listFragment)

        }

        builder.setNegativeButton("NO") { _, _ ->

        }

        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }

}
