package com.json.placeholder.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.json.placeholder.R
import com.json.placeholder.data.PassengerItem
import com.json.placeholder.databinding.ItemPassengerBinding
import com.json.placeholder.ui.base.BasePageAdapter
import com.source.module.app.AppExecutors

/**
 *
 * @author chan youvita
 * @since 2021.04.24
 *
 */
class PassengerAdapter(appExecutors: AppExecutors) : BasePageAdapter<ItemPassengerBinding, PassengerItem, PassengerAdapter.ViewHolder>(
        appExecutors, COMMENT_COMPARATOR) {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_passenger
    }

    override fun setViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(binding)
    }

    /**
     * provide a reference to the type of views that you are using custom view holder
     * @param binding item binding
     */
    inner class ViewHolder(val binding: ItemPassengerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindingItem(item: PassengerItem?) {
            item?: return
            binding.viewModel = item
        }
    }

    companion object {
        val COMMENT_COMPARATOR = object : DiffUtil.ItemCallback<PassengerItem>() {
            override fun areItemsTheSame(oldItem: PassengerItem, newItem: PassengerItem) =
                    oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: PassengerItem, newItem: PassengerItem) =
                    oldItem == newItem
        }
    }

    override fun setBindData(holder: ViewHolder, data: PassengerItem?, position: Int) {
        holder.bindingItem(data)
    }
}