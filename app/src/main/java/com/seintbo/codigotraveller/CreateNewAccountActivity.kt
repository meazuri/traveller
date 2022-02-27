package com.seintbo.codigotraveller

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.seintbo.codigotraveller.databinding.ActivityCreateNewAccountBinding
import com.seintbo.codigotraveller.viewModel.CreateNewViewModel


class CreateNewAccountActivity : AppCompatActivity() {

    private lateinit var createNewViewModel: CreateNewViewModel
    private lateinit var binding: ActivityCreateNewAccountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar ?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val firstName = binding.firstname
        val lastName = binding.lastname
        val email = binding.emailaddress
        val dateOfBirth = binding.dateOfBirth
        val gender = binding.genderswitch
        val nationality = binding.nationality
        val residence = binding.countryOfResidence
        val mobileNo = binding.mobileNumber
        val btnCreateAccount = binding.createAccountBtn

        dateOfBirth.inputType = InputType.TYPE_CLASS_DATETIME

        createNewViewModel = ViewModelProvider(this)
            .get(CreateNewViewModel::class.java)

        createNewViewModel.NewAccountFormState.observe(this, Observer {
            val createAccState = it ?: return@Observer
           // binding.createAccountBtn.isEnabled = createAccState.isDataValid

            Log.d("Observer" ,"NewAccountFormState")
            if (createAccState.firstNameError != null) {
                firstName.error = getString(createAccState.firstNameError)
            }
            if(createAccState.lastNameError != null){
                lastName.error = getString(createAccState.lastNameError)
            }
            if(createAccState.emailError != null){
                email.error = getString(createAccState.emailError)
            }
            if(createAccState.dateOfBirthError != null){
                dateOfBirth.error = getString(createAccState.dateOfBirthError)
            }
            if(createAccState.nationalityError != null){
                nationality.error = getString(createAccState.nationalityError)
            }
            if(createAccState.countryOfResidenceError != null){
                residence.error = getString(createAccState.countryOfResidenceError)
            }

        })

        firstName.afterTextChanged {
            createNewViewModel.firstNameDataChange(firstname = firstName.text.toString())

        }
        lastName.afterTextChanged {
            createNewViewModel.lastNameDataChanged(lastName = lastName.text.toString())
        }

        email.afterTextChanged {
            createNewViewModel.emailChanged(email = email.text.toString())

        }
        dateOfBirth.afterTextChanged {
            createNewViewModel.dateOfBirthDataChanged(dob = dateOfBirth.text.toString())

        }

        btnCreateAccount.setOnClickListener {
          if(  createNewViewModel.isFormValid(
                firstname= firstName.text.toString(),
                lastName = lastName.text.toString(),
                email = email.text.toString(),
                dob = dateOfBirth.text.toString(),
                gender = if (gender.isChecked) "M" else "F",
                nationality = nationality.text.toString(),
                residence =  residence.text.toString(),
                mobileNumber = mobileNo.text.toString()

            ))
          {
              Toast.makeText(this,"Create Successfully !",Toast.LENGTH_LONG).show()
          }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}