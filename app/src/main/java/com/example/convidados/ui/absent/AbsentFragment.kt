package com.example.convidados.ui.absent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentAbsentBinding
import com.example.convidados.ui.adapter.GuestAdapter
import com.example.convidados.ui.guestForm.GuestFormActivity
import com.example.convidados.ui.listener.OnGuestListener

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private lateinit var absentViewModel: AbsentViewModel

    private val binding get() = _binding!!
    private var adapter : GuestAdapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        absentViewModel = ViewModelProvider(this).get(AbsentViewModel::class.java)
        addObserve()
        setupRecyclerView()
    }

    override fun onResume() {
        absentViewModel.getAllAbsents()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(){
        adapter = GuestAdapter()

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.GUEST_ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                Log.d("***teste", "onDelete clicado ${id}")
                absentViewModel.delete(id)
                absentViewModel.getAllAbsents()
            }

        }

        adapter.attachListener(listener)
        binding.recyclerAllAbsentsGuests.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerAllAbsentsGuests.adapter = adapter
    }

    private fun addObserve(){
        absentViewModel.guestsAbsent.observe(viewLifecycleOwner){
            adapter.updateAbsentsGuests(it)
        }
    }

}