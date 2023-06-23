package com.example.convidados.ui.guestForm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel

class GuestFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.raButtonPresent.isChecked = true

        observe()
        setListeners()
        loadData()
    }

    private fun setListeners(){
        binding.btSave.setOnClickListener{
            val name = binding.edUserName.text.toString()
            val presence = binding.raButtonPresent.isChecked

            val model = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }

            viewModel.insert(model)
//            Toast.makeText(this, "Convidado adicionado com sucesso!",
//                Toast.LENGTH_LONG).show();
        }

        binding.btUpdate.setOnClickListener {
            val name = binding.edUserName.text.toString()

            val presence = binding.raButtonPresent.isChecked

            val model = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }
            viewModel.update(model)

            //Todo temp
            finish()
        }
    }

    private fun observe(){
        viewModel.guest.observe(this) {
            binding.edUserName.setText(it.name)
            if(it.presence) {
                binding.raButtonPresent.isChecked = true
            } else {
                binding.raButtonAbsent.isChecked = true
            }
        }

        viewModel.saveGuest.observe(this){
            if(it != ""){
                Toast.makeText(this, it, Toast.LENGTH_LONG).show();
                finish()
            }
        }
    }

    private fun loadData(){
        val bundle = intent.extras
        if(bundle != null){
            guestId = bundle.getInt(DataBaseConstants.GUEST.GUEST_ID)
            viewModel.get(guestId)
        }
    }

}