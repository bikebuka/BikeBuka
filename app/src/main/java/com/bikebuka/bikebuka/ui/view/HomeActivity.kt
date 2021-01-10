package com.bikebuka.bikebuka.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bikebuka.bikebuka.R
import com.bikebuka.bikebuka.ui.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    lateinit var homeRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeRecyclerView = findViewById(R.id.home_recycler)
        homeRecyclerView.apply {

        }
    }
}