package com.json.placeholder.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.json.placeholder.R
import com.json.placeholder.app.Constants
import com.json.placeholder.databinding.ActivityMainBinding
import com.json.placeholder.ui.base.BaseActivity
import com.json.placeholder.ui.adapter.CommentAdapter
import com.json.placeholder.ui.adapter.PassengerAdapter
import com.json.placeholder.ui.details.DetailsActivity
import com.source.module.rxjava.RxEvent
import com.source.module.rxjava.RxJava
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
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

    @ExperimentalPagingApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        getCommentList()
//        binding.viewModel = viewModel
        

//        getPassengerList()

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
        binding.rvComment.layoutManager = LinearLayoutManager(this@MainActivity)
        commentAdapter.setCommentClickListener {_, pos ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(Constants.COMMENTS_ITEM_KEY, commentAdapter.currentList[pos])
            startActivity(intent)
        }
//        viewModel.getComments()

////        binding.viewModel = viewModel
//        viewModel.comments.map {
////            Log.d(">>>>>","${it.data}")
//            commentAdapter.submitList(it.data)
//            binding.itemCount = commentAdapter.itemCount
//        }
//        (this) {
//            if (it.data.isNullOrEmpty()) return@observe
//            Log.d(">>>>", "result:: ${viewModel.comments.value?.status}")
//            if (it is Resource.Success) {
//                commentAdapter.addItemList(it.data)
//                binding.itemCount = commentAdapter.itemCount
//                viewModel.cancelRequests()
//            }
//        }

        // state flow
        val job = lifecycleScope.launch {

            launch {
                viewModel.get().collect { comments ->
                    Log.d(">>>","state::: ${comments.status}")
                    Log.d(">>>","data::: ${comments.data}")

                    if (!comments.data.isNullOrEmpty()) {
                        commentAdapter.submitList(comments.data)
                        binding.itemCount = commentAdapter.itemCount
                    }
                }
            }
        }

        job.invokeOnCompletion {
        }

        // live data
//        viewModel.comments.observe(this@MainActivity) { comments ->
//            Log.d(">>>>", "result:: ${comments.status}")
////            if (comments is Resource.Success) {
//                commentAdapter.submitList(comments.data)
//                binding.itemCount = commentAdapter.itemCount
//                viewModel.cancelRequests()
////            }
//        }
    }

    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    private fun getPassengerList() {
        binding.rvComment.adapter = passengerAdapter

        viewModel.getPassenger()
//        viewModel.dataPassenger.observe(this@MainActivity) {
//
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        commentDisposable?.dispose()
        commentDisposable = null
    }

    @ExperimentalCoroutinesApi
    override fun onResume() {
        super.onResume()

        getCommentList()

//        if (commentAdapter.itemCount < 0) {
//            shimmerFrameLayout = binding.shimmer
//            shimmerFrameLayout?.startShimmer()
//        }
    }
}