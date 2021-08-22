package com.json.placeholder.ui.card

import android.os.Bundle
import com.json.placeholder.R
import com.json.placeholder.data.CommentsItem
import com.json.placeholder.databinding.ActivityCardviewBinding
import com.json.placeholder.ui.adapter.CommentAdapter
import com.json.placeholder.ui.base.BaseActivity
import com.source.module.custom.CardScaleHelper
import com.source.module.custom.ItemOffsetDecoration
import com.source.module.di.AppModule
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class CardActivity: BaseActivity<ActivityCardviewBinding>() {

    @Inject
    lateinit var cardScaleHelper: CardScaleHelper

    @Inject
    @Named(AppModule.OFFSET_SPACING_SMALL)
    lateinit var itemOffsetDecoration: ItemOffsetDecoration

    @Inject
    lateinit var commentAdapter: CommentAdapter

    override fun getLayoutId(): Int = R.layout.activity_cardview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list = mutableListOf<CommentsItem>()

        val item = CommentsItem()
        item.postId = 1
        item.id = 1
        item.name = "Dara"
        item.email = "Dara@gmail"
        item.body = "Dara Kun"
        list.add(0, item)

        val item1 = CommentsItem()
        item1.postId = 2
        item1.id = 2
        item1.name = "Sokra"
        item1.email = "Sokra@gmail"
        item1.body = "Sokra Kun"
        list.add(1, item1)

        val item2 = CommentsItem()
        item2.postId = 3
        item2.id = 3
        item2.name = "Sokra"
        item2.email = "Sokra@gmail"
        item2.body = "Sokra Kun"
        list.add(2, item2)

        binding.rvHotel.adapter = commentAdapter
        binding.rvHotel.removeItemDecoration(itemOffsetDecoration)
        binding.rvHotel.addItemDecoration(itemOffsetDecoration)
        cardScaleHelper.attachToRecyclerView(binding.rvHotel)
        cardScaleHelper.setScale(1f)
//        cardScaleHelper.setPagePadding(0)
//        cardScaleHelper.setShowLeftCardWidth(0)

        commentAdapter.submitList(list)


    }
}