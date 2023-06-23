package com.example.convidados.ui.absent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class AbsentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val _listGuestsAbsents = MutableLiveData<List<GuestModel>>()
    val guestsAbsent : MutableLiveData<List<GuestModel>> = _listGuestsAbsents

    fun getAllAbsents(){
        _listGuestsAbsents.value = repository.getAbsent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}