package com.seintbo.codigotraveller.viewModel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seint.takehome.isValidBirthday
import com.seintbo.codigotraveller.CreateNewAccountResult
import com.seintbo.codigotraveller.NewAccountFormState
import com.seintbo.codigotraveller.R
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Seint San Thandar Bo on 26/2/22.
 */
class CreateNewViewModel ():ViewModel(){

    private val _newAccountForm = MutableLiveData<NewAccountFormState>()
    val NewAccountFormState: LiveData<NewAccountFormState> = _newAccountForm

    private val _createAccResult = MutableLiveData<CreateNewAccountResult>()
    val loginResult: LiveData<CreateNewAccountResult> = _createAccResult

    fun firstNameDataChange(firstname: String
                   ){
        if( firstname.isBlank()){
            _newAccountForm.value = NewAccountFormState(firstNameError = R.string.empty_userName)
            Log.d("firstNameDataChange" ,"isBlank")

        }

    }
    fun lastNameDataChanged(lastName: String){
        if( lastName.isBlank()){
            _newAccountForm.value = NewAccountFormState(lastNameError = R.string.empty_lastName)

        }
    }
    fun dateOfBirthDataChanged(dob:String) {
        if (!isValidDateOfBirth(dob)) {
            _newAccountForm.value = NewAccountFormState(dateOfBirthError = R.string.invalid_date)
        }
    }
    fun emailChanged(email: String){
        if(!isValidEmail(email)){
            _newAccountForm.value = NewAccountFormState(emailError = R.string.invalid_email)
        }
    }


     fun isFormValid(firstname: String,lastName: String,email:String,dob:String,gender:String
                                    , nationality:String,residence:String,mobileNumber:String):Boolean{
        if( firstname.isBlank()){
            _newAccountForm.value = NewAccountFormState(firstNameError = R.string.empty_userName)
            Log.d("firstNameDataChange" ,"isBlank")
            return false

        }else if(lastName.isBlank()){
            _newAccountForm.value = NewAccountFormState(lastNameError = R.string.empty_userName)
            return false

        }else if(!isValidEmail(email)){
            _newAccountForm.value = NewAccountFormState(emailError = R.string.invalid_email)
            return false

        }else if(!isValidDateOfBirth(dob)){
            _newAccountForm.value = NewAccountFormState(dateOfBirthError = R.string.invalid_date)
            return false

        }else if(nationality.isEmpty()){
            _newAccountForm.value = NewAccountFormState(nationalityError = R.string.invalid_nationality)
            return false

        }else if( residence.isEmpty()){
            _newAccountForm.value = NewAccountFormState(dateOfBirthError = R.string.invalid_residence)
            return false

        }else{
            _newAccountForm.value = NewAccountFormState(isDataValid = true)
            return true


        }
    }
    private fun isValidEmail(email: String):Boolean{

        if(email.isEmpty()){
            return false;
        } else
        {
           return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
    private fun isValidDateOfBirth(dob: String):Boolean{
        if (dob.isEmpty()){
            _newAccountForm.value = NewAccountFormState(dateOfBirthError = R.string.empty_dob)
            return false
        }else{
            val valid_bday =  "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)"
            var validbday: Matcher = Pattern.compile(valid_bday).matcher(dob);

            if(!validbday.matches())
            {
                _newAccountForm.value = NewAccountFormState(dateOfBirthError = R.string.invalid_date)
                return false
            }else if(!isValidBirthday(dob)) {
                _newAccountForm.value = NewAccountFormState(dateOfBirthError = R.string.invalid_date)
                return false
            }else{
                return true
            }
        }
    }


}