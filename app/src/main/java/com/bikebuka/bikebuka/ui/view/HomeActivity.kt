package com.bikebuka.bikebuka.ui.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bikebuka.bikebuka.R
import com.bikebuka.bikebuka.databinding.ActivityHomeBinding
import com.bikebuka.bikebuka.domain.Bike
import com.bikebuka.bikebuka.ui.viewmodel.HomeViewModel
import com.facebook.shimmer.ShimmerFrameLayout

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    lateinit var homeRecyclerView: RecyclerView
    lateinit var binding: ActivityHomeBinding
    lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private val homeAdapter by lazy {
        HomeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeRecyclerView = findViewById(R.id.home_recycler)
        val bikes = ArrayList<Bike>()
        bikes.add(Bike(name = "Mountain", description = "A good bike", price = "100", id = 1))
        bikes.add(Bike(name = "Mountain", description = "A good bike", price = "100", id = 2))
        bikes.add(Bike(name = "Mountain", description = "A good bike", price = "100", id = 3))
        bikes.add(Bike(name = "Mountain", description = "A good bike", price = "100", id = 4))
        bikes.add(Bike(name = "Mountain", description = "A good bike", price = "100", id = 5))
        homeAdapter.addItems(bikes)

        Handler().postDelayed({
            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            homeRecyclerView.apply {
                visibility = View.VISIBLE
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = homeAdapter
            }
        }, 5000)
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }
}