package com.example.convidados.ui.present

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentPresentBinding
import com.example.convidados.ui.adapter.GuestAdapter
import com.example.convidados.ui.guestForm.GuestFormActivity
import com.example.convidados.ui.listener.OnGuestListener

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private lateinit var presentViewModel: PresentViewModel

    private val binding get() = _binding!!
    private var adapter : GuestAdapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presentViewModel = ViewModelProvider(this).get(PresentViewModel::class.java)
        addObserve()
        setupRecyclerView()
    }

    override fun onResume() {
        presentViewModel.getAllPresent()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
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
                presentViewModel.delete(id)
                presentViewModel.getAllPresent()
            }

        }

        adapter.attachListener(listener)
        binding.recyclerAllPresentsGuests.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerAllPresentsGuests.adapter = adapter
    }

    private fun addObserve() {
        presentViewModel.guestsPresent.observe(viewLifecycleOwner) {
            adapter.updatePresentsGuests(it)
        }
    }
}