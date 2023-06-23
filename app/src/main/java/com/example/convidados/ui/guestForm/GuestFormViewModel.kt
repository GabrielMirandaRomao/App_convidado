package com.example.convidados.ui.guestForm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest : LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest : LiveData<String> = _saveGuest

    fun insert(guestModel: GuestModel){
        if(repository.insert(guestModel)){
            _saveGuest.value = "Convidado adicionado com sucesso!"
        } else {
            _saveGuest.value = "Falha na execução"
        }
    }

    fun update(guestModel: GuestModel){
        if(repository.update(guestModel)){
            _saveGuest.value = "Convidado atualizado com sucesso!"
        } else {
            _saveGuest.value = "Falha na execução"
        }
    }

    fun get(id: Int){
        guestModel.value = repository.get(id)
    }
}