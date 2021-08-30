package com.json.placeholder.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.facebook.shimmer.ShimmerFrameLayout
import com.json.placeholder.R
import com.json.placeholder.databinding.ActivityMainBinding
import com.json.placeholder.ui.base.BaseActivity
import com.json.placeholder.ui.adapter.CommentAdapter
import com.json.placeholder.ui.adapter.PassengerAdapter
import com.source.module.data.Resource
import com.source.module.data.Status
import com.source.module.rxjava.RxEvent
import com.source.module.rxjava.RxJava
import dagger.Component
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private var commentDisposable: Disposable? = null


    private var shimmerFrameLayout: ShimmerFrameLayout? = null

    @Inject
    lateinit var commentAdapter: CommentAdapter

    @Inject
    lateinit var passengerAdapter: PassengerAdapter

    override fun getLayoutId(): Int = R.layout.activity_main

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        getCommentList()
//        binding.viewModel = viewModel
        

        getPassengerList()

        commentDisposable = RxJava.listen(RxEvent.CommentSuccess::class.java).subscribe {
            Log.d(">>>>","Result::: ${it.value}")
        }
    }

    /**
     * get comments
     */
    @ExperimentalCoroutinesApi
    private fun getCommentList() {
        binding.rvComment.adapter = commentAdapter
//        viewModel.getComments()

//        binding.viewModel = viewModel
//        viewModel.comments.observe(this) {
//            if (it.data.isNullOrEmpty()) return@observe
//            Log.d(">>>>", "result:: ${viewModel.comments.value?.status}")
//            if (it is Resource.Success) {
//                commentAdapter.addItemList(it.data)
//                binding.itemCount = commentAdapter.itemCount
//                viewModel.cancelRequests()
//            }
//        }

//        viewModel.comments.observe(this@MainActivity) { comments ->
////            if (comments is Resource.Success) {
//                commentAdapter.submitList(comments.data as MutableList<CommentsItem>?)
//                binding.itemCount = commentAdapter.itemCount
//                viewModel.cancelRequests()
////            }
//        }
    }

    @ExperimentalCoroutinesApi
    private fun getPassengerList() {
        binding.rvComment.adapter = passengerAdapter
        viewModel.passenger
        viewModel.dataPassenger.observe(this@MainActivity) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        commentDisposable?.dispose()
        commentDisposable = null
    }

    @ExperimentalCoroutinesApi
    override fun onResume() {
        super.onResume()

//        getCommentList()

//        if (commentAdapter.itemCount < 0) {
//            shimmerFrameLayout = binding.shimmer
//            shimmerFrameLayout?.startShimmer()
//        }
    }
}