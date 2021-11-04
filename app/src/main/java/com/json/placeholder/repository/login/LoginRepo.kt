package com.json.placeholder.repository.login

class LoginRepo {

    /**
     * handle verify login
     */
    fun checkValidLogin(email: String, password: String) : Boolean {
        if (email == "1" && password == "1") {
            return true
        }
        return false
    }

    /**
     * handle verify email & password
     * @param email user email
     * @param password user password
     */
    fun checkValidEmailPassword(email: String, password: String) : Boolean {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            return true
        }
        return false
    }
}