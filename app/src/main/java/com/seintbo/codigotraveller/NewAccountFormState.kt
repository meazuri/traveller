package com.seintbo.codigotraveller

/**
 * Created by Seint San Thandar Bo on 26/2/22.
 */
data class NewAccountFormState (
        val firstNameError :Int? = null,
        val lastNameError: Int? = null,
        val emailError: Int? = null,
        val dateOfBirthError :Int? = null,
        val nationalityError: Int? = null,
        val countryOfResidenceError: Int? = null,
        val mobileNumberError: String? = null,
        val isDataValid: Boolean = false

)