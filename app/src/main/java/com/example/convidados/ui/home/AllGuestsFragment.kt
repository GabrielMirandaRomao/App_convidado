package com.example.convidados.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentAllGuestsBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.ui.adapter.GuestAdapter
import com.example.convidados.ui.guestForm.GuestFormActivity
import com.example.convidados.ui.listener.OnGuestListener

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private lateinit var homeViewModel: AllGuestsViewModel

    private val binding get() = _binding!!
    private var adapter : GuestAdapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        addObserve()
        setupRecyclerView()
    }

    override fun onResume() {
        homeViewModel.getAll()
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
                homeViewModel.delete(id)
                homeViewModel.getAll()
            }

        }

        adapter.attachListener(listener)

        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerAllGuests.adapter = adapter
    }

    private fun addObserve() {
        homeViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuest(it)
        }
    }
}