package com.example.convidados.ui.present

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class PresentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val _listGuestsPresent = MutableLiveData<List<GuestModel>>()
    val guestsPresent : LiveData<List<GuestModel>> = _listGuestsPresent

    fun getAllPresent(){
        _listGuestsPresent.value = repository.getPresent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}