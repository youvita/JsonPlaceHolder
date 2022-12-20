package com.json.placeholder.ui.adapter

import android.view.View
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
 * @since 2022.11.27
 *
 */
class CommentAdapter(appExecutors: AppExecutors) : BaseAdapter<ItemCommentBinding, CommentsItem, CommentAdapter.ViewHolder>
    (appExecutors, COMMENT_COMPARATOR){

    /** comment call back function */
    private var commentClicked: ((view: View, pos: Int) -> Unit)? = null

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_comment
    }

    override fun setViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(binding)
    }

    override fun setBindData(holder: ViewHolder, data: CommentsItem, position: Int) {
        holder.binding.viewModel = data
    }

    /**
     * set comment on click listener
     */
    fun setCommentClickListener(commentClicked: (view: View, pos: Int) -> Unit) =
        apply { this.commentClicked = commentClicked }

    /**
     * provide a reference to the type of views that you are using custom view holder
     * @param binding item binding
     */
    inner class ViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.tvComment.setOnClickListener {
                commentClicked?.invoke(it, absoluteAdapterPosition)
            }
        }
    }

    companion object {
        val COMMENT_COMPARATOR = object : DiffUtil.ItemCallback<CommentsItem>() {
            override fun areItemsTheSame(oldItem: CommentsItem, newItem: CommentsItem) =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CommentsItem, newItem: CommentsItem) =
                    oldItem == newItem
        }
    }
}