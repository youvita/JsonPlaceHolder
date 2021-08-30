package com.json.placeholder.ui.main

import androidx.lifecycle.*
import com.json.placeholder.data.CommentsItem
import com.json.placeholder.data.PassengerItem
import com.json.placeholder.repository.comment.CommentRepo
import com.json.placeholder.repository.passenger.PassengerRepo
import com.json.placeholder.ui.base.BaseViewModel
import com.source.module.data.Listing
import com.source.module.data.PagingResponse
import com.source.module.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val passengerRepo: PassengerRepo) : BaseViewModel() {

//    private val _comments: MutableLiveData<Resource<List<CommentsItem>>> = MutableLiveData()
//    val comments: LiveData<Resource<List<CommentsItem>>> get() = _comments

//    private val _comments: MutableLiveData<PassengerItem> = MutableLiveData()
//    val comments: LiveData<CommentsItem> get() = _comments

//    fun getComments(){
//        viewModelScope.launch {
////            commentRepo.loadPassenger()
////            commentRepo.getComments().onEach { resource -> _comments.value = resource }.launchIn(viewModelScope)
////            commentRepo.loadPassenger().onEach { comments -> _comments.value = comments }.launchIn(viewModelScope)
//        }
//    }

    @ExperimentalCoroutinesApi
//    val comments = commentRepo.getCommentBound().asLiveData()

//    private val _options = MutableLiveData<HashMap<String, String?>>()

    private val repoResult = MutableLiveData<Listing<PassengerItem>>()

    @ExperimentalCoroutinesApi
    val passenger = repoResult.switchMap {
        liveData {
            val data = passengerRepo.loadPassenger()
            emit(data)
        }
    }
//
//    val accessToken = liveData {
//        val token = credentialRepo.getAccessToken()
//        emitSource(token)
//    }

//    fun getPassenger() {
//        viewModelScope.launch {
//            passengerRepo.loadPassenger(viewModelScope)
////            commentRepo.getPassenger().onEach { comments -> _comments.value = comments }.launchIn(viewModelScope)
//        }
//    }

    @ExperimentalCoroutinesApi
    val dataPassenger = Transformations.switchMap(repoResult) {
        it.pagedList
    }
}
