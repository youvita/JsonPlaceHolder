package com.json.placeholder.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.json.placeholder.R
import com.json.placeholder.data.CommentsItem
import com.json.placeholder.databinding.ItemCommentBinding
import com.json.placeholder.ui.base.BaseAdapter
import com.source.module.app.AppExecutors

/**
 *
 * @author chan youvita
 * @since 2021.04.24
 *
 */
class CommentAdapter(appExecutors: AppExecutors) : BaseAdapter<ItemCommentBinding, CommentsItem, CommentAdapter.ViewHolder>(
        appExecutors, COMMENT_COMPARATOR) {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_comment
    }

    override fun setViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(binding)
    }

    override fun setBindData(holder: ViewHolder, data: CommentsItem, position: Int) {
        binding.viewModel = data
    }

    /**
     * provide a reference to the type of views that you are using custom view holder
     * @param binding item binding
     */
    class ViewHolder(binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val COMMENT_COMPARATOR = object : DiffUtil.ItemCallback<CommentsItem>() {
            override fun areItemsTheSame(oldItem: CommentsItem, newItem: CommentsItem) =
                    oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CommentsItem, newItem: CommentsItem) =
                    oldItem == newItem
        }
    }
}