package com.json.placeholder.ui.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
//import com.source.module.app.ViewModelFactory
import com.source.module.util.LocaleHelper
import javax.inject.Inject

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

//    @Inject
//    lateinit var factory: ViewModelProvider.Factory
//
//    val viewModelFactory by lazy { ViewModelProvider(this, factory)}

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}