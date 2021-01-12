package com.bikebuka.bikebuka.ui.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bikebuka.bikebuka.R
import com.bikebuka.bikebuka.databinding.ActivityHomeBinding
import com.bikebuka.bikebuka.domain.Bike
import com.bikebuka.bikebuka.ui.viewmodel.HomeViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.datepicker.MaterialDatePicker

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    lateinit var homeRecyclerView: RecyclerView
    lateinit var binding: ActivityHomeBinding
    lateinit var shimmerFrameLayout: ShimmerFrameLayout
    lateinit var pickupDate: ConstraintLayout
    lateinit var returnDate: ConstraintLayout
    private val homeAdapter by lazy {
        HomeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout)
        pickupDate = findViewById(R.id.pick_date)
        returnDate = findViewById(R.id.return_date)
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
        pickupDate.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()
            picker.show(supportFragmentManager, picker.toString())
            picker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "Picker Cancelled", Toast.LENGTH_LONG).show()
            }
            picker.addOnPositiveButtonClickListener {
                val text: TextView = findViewById(R.id.textView5)
                text.text = picker.headerText
                Toast.makeText(this, "Date: ${picker.headerText}", Toast.LENGTH_LONG).show()
            }
        }

        returnDate.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()
            picker.show(supportFragmentManager, picker.toString())
            picker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "Picker Cancelled", Toast.LENGTH_LONG).show()
            }
            picker.addOnPositiveButtonClickListener {
                val text: TextView = findViewById(R.id.textView9)
                text.text = picker.headerText
                Toast.makeText(this, "Date: ${picker.headerText}", Toast.LENGTH_LONG).show()
            }
        }
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