package com.json.placeholder.repository.passenger

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.json.placeholder.data.PassengerItem
import com.json.placeholder.data.api.PassengerApi
import com.source.module.data.Listing
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PassengerRepo(private val passengerApi: PassengerApi,private val pageListConfig: PagedList.Config) {

    suspend fun loadPassenger(
        scope: CoroutineScope
    ): Listing<PassengerItem> {
        val sourceFactory = PassengerDataSourceFactory(scope, passengerApi)

        val livePagedList: LiveData<PagedList<PassengerItem>> =
            LivePagedListBuilder(sourceFactory, pageListConfig).build()

        val networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState }

        return Listing(
            pagedList = livePagedList,
            networkState = networkState,
            retry = { sourceFactory.sourceLiveData.value?.retryDataItem() },
            refresh = { sourceFactory.sourceLiveData.value?.invalidate() }
        )
    }

    fun loadPassenger(){}
}