package com.json.placeholder.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.json.placeholder.data.request.LoginRequest
import com.json.placeholder.repository.login.LoginRepo
import com.json.placeholder.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo): BaseViewModel() {

    /** handle live data call back to activity */
    private val _login: MutableLiveData<Boolean> = MutableLiveData()
    val login: LiveData<Boolean> get() = _login

    /** handle request data class */
    var loginRequest = LoginRequest()

    /**
     * handle banding on click event
     */
    fun onLoginClick() {
        _login.postValue(loginRepo.checkValidLogin(
                loginRequest.email.orEmpty(),
                loginRequest.password.orEmpty()))
    }

    /**
     * handle enable/disable valid login button
     */
    fun enableLogin() : Boolean {
        return loginRepo.checkValidEmailPassword(loginRequest.email.orEmpty(), loginRequest.password.orEmpty())
    }

    fun setData(st: String) {
        Log.d(">>>>", st)
    }

}