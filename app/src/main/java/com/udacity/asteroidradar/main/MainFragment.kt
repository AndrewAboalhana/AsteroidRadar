package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(this, MainViewModel.Factory(activity.application)).get(MainViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter=MainAdapter(MainAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToDetailData.observe(viewLifecycleOwner, Observer {
         if(null != it){
               this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
             viewModel.displayPropertyDetailsCompleted()
         }
        })

        binding.asteroidRecycler.adapter =adapter
        viewModel.property.observe(viewLifecycleOwner){ data ->
            Log.d("_property",data.toString())
            adapter.submitList(data)
        }



        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.show_all_menu ->
                viewModel.displayWeekAsteroid()
            R.id.show_rent_menu ->
                viewModel.displayTodayAsteroid()
            R.id.show_buy_menu ->
                viewModel.displaySavedAsteroid()
        }
        return true
    }
}

