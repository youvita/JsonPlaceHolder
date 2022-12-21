package com.json.placeholder.ui.details

import android.os.Bundle
import android.view.MenuItem
import com.json.placeholder.R
import com.json.placeholder.databinding.ActivityDetailsBinding
import com.json.placeholder.ui.base.BaseActivity

class DetailsActivity: BaseActivity<ActivityDetailsBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init action bar
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_left)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}