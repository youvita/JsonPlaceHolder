package com.json.placeholder.ui.main

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.json.placeholder.data.CommentsItem
import com.json.placeholder.data.PassengerItem
import com.json.placeholder.repository.comment.CommentRepo
import com.json.placeholder.repository.passenger.PassengerRepo
import com.json.placeholder.ui.base.BaseViewModel
//import com.source.module.data.Listing
import com.source.module.data.Resource
import com.source.module.data.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val commentRepo: CommentRepo, private val passengerRepo: PassengerRepo) : BaseViewModel() {


//    private val _comments: MutableLiveData<Resource<List<CommentsItem>>> = MutableLiveData()
//    val comments: LiveData<Resource<List<CommentsItem>>> get() = _comments

//    private val _comments: MutableLiveData<PassengerItem> = MutableLiveData()
//    val comments: LiveData<CommentsItem> get() = _comments

//    fun getComments(){
//        viewModelScope.launch {
////            commentRepo.loadPassenger()
//            commentRepo.getCommentBound().onEach { resource -> _comments.value = resource }.launchIn(viewModelScope)
////            commentRepo.loadPassenger().onEach { comments -> _comments.value = comments }.launchIn(viewModelScope)
//        }
//    }


//    val comments: StateFlow<Resource<List<CommentsItem>>>
        suspend fun get() = commentRepo.getCommentBound(1)
                .stateIn(viewModelScope, SharingStarted.Eagerly, Resource.Loading())


//    @ExperimentalCoroutinesApi
//    val comments = commentRepo.getCommentBound()

//    private val _options = MutableLiveData<HashMap<String, String?>>()

//    private val repoResult = MutableLiveData<Listing<PassengerItem>>()

//    @ExperimentalCoroutinesApi
//    val passenger = repoResult.switchMap {
//        liveData {
//            val data = passengerRepo.loadPassenger(viewModelScope)
//            emit(data)
//        }
//    }

    private var currentResult: Flow<PagingData<PassengerItem>>? = null

    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    fun searchPlayers(): Flow<PagingData<PassengerItem>> {
        val newResult: Flow<PagingData<PassengerItem>> = passengerRepo.getPlayers().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

    /**
     * Same thing but with Livedata
     */
    private var currentResultLiveData: LiveData<PagingData<PassengerItem>>? = null

//    fun searchPlayersLiveData(): LiveData<PagingData<PassengerItem>> {
//        val newResultLiveData: LiveData<PagingData<PassengerItem>> =
//        currentResultLiveData = newResultLiveData
//        return newResultLiveData
//    }
//
//    val accessToken = liveData {
//        val token = credentialRepo.getAccessToken()
//        emitSource(token)
//    }

    fun getPassenger() {
        viewModelScope.launch {
            passengerRepo.getPlayers().onEach {

            }.launchIn(viewModelScope)
//            commentRepo.getPassenger().onEach { comments -> _comments.value = comments }.launchIn(viewModelScope)
        }
    }

//    @ExperimentalCoroutinesApi
//    val dataPassenger = Transformations.switchMap(repoResult) {
//        it.pagedList
//    }
}
