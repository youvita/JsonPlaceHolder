package com.json.placeholder.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.source.module.app.AppExecutors

/**
 *
 * customize adapter to reusable
 *
 * @author chan youvita
 * @since 2021.04.24
 *
 */
abstract class BasePageAdapter<B: ViewDataBinding, T ,VH: RecyclerView.ViewHolder>(
        appExecutors: AppExecutors,
        diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder<T>(diffCallback)
        .setBackgroundThreadExecutor(appExecutors.diskIO())
        .build()) {

    /** handle item binding */
    lateinit var binding: B

    /** handle item layout */
    abstract fun getLayoutId(viewType: Int): Int

    /** handle custom view holder */
    abstract fun setViewHolder(parent: ViewGroup): VH

    /** handle bind data */
    abstract fun setBindData(holder: VH, data: T?, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, getLayoutId(viewType), parent, false)
        return setViewHolder(parent)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        setBindData(holder as VH, getItem(position), position)
    }

}