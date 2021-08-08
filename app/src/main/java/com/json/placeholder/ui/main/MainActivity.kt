package com.json.placeholder.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facebook.shimmer.ShimmerFrameLayout
import com.json.placeholder.R
import com.json.placeholder.databinding.ActivityMainBinding
import com.json.placeholder.ui.base.BaseActivity
import com.json.placeholder.ui.adapter.CommentAdapter
import com.source.module.data.Resource
import com.source.module.data.Status
import com.source.module.rxjava.RxEvent
import com.source.module.rxjava.RxJava
import dagger.Component
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private var commentDisposable: Disposable? = null


    private var shimmerFrameLayout: ShimmerFrameLayout? = null

    @Inject
    lateinit var commentAdapter: CommentAdapter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        getCommentList()

        commentDisposable = RxJava.listen(RxEvent.CommentSuccess::class.java).subscribe {
            Log.d(">>>>","Result::: ${it.value}")
        }
    }

    /**
     * get comments
     */
    private fun getCommentList() {
        binding.rvComment.adapter = commentAdapter
        viewModel.getComments()
        viewModel.dataPassenger.observe(this) {
        }

        binding.commentsItem = viewModel.comments
        viewModel.comments.observe(this) {
            if (it.data.isNullOrEmpty()) return@observe
            Log.d(">>>>", "result:: ${viewModel.comments.value?.status}")
            if (it is Resource.Success) {
                commentAdapter.addItemList(it.data)
                binding.itemCount = commentAdapter.itemCount
                viewModel.cancelRequests()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        commentDisposable?.dispose()
        commentDisposable = null

        commentAdapter.clearItemList()
    }

    override fun onResume() {
        super.onResume()
        getCommentList()

        if (commentAdapter.itemCount < 0) {
            shimmerFrameLayout = binding.shimmer
            shimmerFrameLayout?.startShimmer()
        }
    }
}