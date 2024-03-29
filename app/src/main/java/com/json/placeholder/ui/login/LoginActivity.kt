package com.json.placeholder.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.json.placeholder.R
import com.json.placeholder.data.CommentsItem
import com.json.placeholder.databinding.ActivityLoginBinding
import com.json.placeholder.ui.base.BaseActivity
import com.json.placeholder.ui.card.CardActivity
import com.json.placeholder.ui.language.LanguageDialog
import com.json.placeholder.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.*


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModels()

    private val item: CommentsItem by lazy { CommentsItem() }

    private lateinit var name: String

    lateinit var parentJob: Job

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        item.name = "Dara"

//        name = "Ddddd"

//        val b = name

//        Log.d(">>>>", item?.name ?: "Dara")

//        name?.let {
//            Log.d(">>>>", it)
//        }


//        if (this::item.isInitialized) {
//
//        }

//        Log.d(">>>>", name)
//        if (this::name.isInitialized) {
//            Log.d(">>>>", name)
//        }

        binding.viewModel = loginViewModel
        binding.localeCode = Locale.getDefault().language
        binding.etEmail.addTextChangedListener(textWatcher)
        binding.etPassword.addTextChangedListener(textWatcher)


        loginRequest()

        chooseLanguage()

        main()

    }

    private fun main() {
        val startTime = System.currentTimeMillis()
        println("Starting the parent job...")
        parentJob = CoroutineScope(Main).launch {
//            for (i in 1..100_000) {
                launch {
                    delay(2000L)
                    work(1)
                }
                launch {
                    work(2)
                }
//            }
        }
        parentJob.invokeOnCompletion { throwable ->
            if (throwable != null) {
                println("Job was cancel is ${System.currentTimeMillis() - startTime} ms.")
            }else {
                println("Done is ${System.currentTimeMillis() - startTime} ms.")
            }
        }
    }

    private suspend fun work(i: Int) {
        delay(3000)
        println("Work $i done ${Thread.currentThread().name}")
    }

    private fun setName(name: String) {
//        this.name = name
    }

    private fun chooseLanguage() {
        binding.ivLanguage.setOnClickListener {
            LanguageDialog().show(supportFragmentManager, null)
        }
    }

    /**
     * login request observable
     */
    private fun loginRequest() {
        loginViewModel.login.observe(this) {

//            parentJob.cancel()
//            item.name = "Daaaaaammmmmmm"
//            Log.d(">>>>", "${item.name}")
            if (it) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    /**
     * text watcher event changed listener
     */
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {binding.enableLogin = loginViewModel.enableLogin()}
        override fun afterTextChanged(s: Editable?) {}
    }
}