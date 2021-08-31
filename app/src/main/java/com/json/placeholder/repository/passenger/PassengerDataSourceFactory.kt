//package com.json.placeholder.repository.passenger
//
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.DataSource
//import com.json.placeholder.data.PassengerItem
//import com.json.placeholder.data.api.PassengerApi
//import kotlinx.coroutines.CoroutineScope
//import javax.inject.Inject
//import javax.inject.Singleton
//
//class PassengerDataSourceFactory @Inject constructor(
//    private val scope: CoroutineScope,
//    private val api: PassengerApi): DataSource.Factory<Int, PassengerItem>()
//{
//
//    val sourceLiveData = MutableLiveData<PassengerDataSource>()
//
//    override fun create(): DataSource<Int, PassengerItem> {
//        val source = PassengerDataSource(scope, api)
//        sourceLiveData.postValue(source)
//        return source
//    }
//}