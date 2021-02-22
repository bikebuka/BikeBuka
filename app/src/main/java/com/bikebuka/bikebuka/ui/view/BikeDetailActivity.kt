package com.bikebuka.bikebuka.ui.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bikebuka.bikebuka.R
import com.bikebuka.bikebuka.ui.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@AndroidEntryPoint
class BikeDetailActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private lateinit var bikeImage: ImageView
    private lateinit var bikeName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_detail)
        bikeImage = findViewById(R.id.bikeImage)
        bikeName = findViewById(R.id.bikeName)
        val id = intent.getStringExtra("id")
        compositeDisposable.add(
            viewModel.getBikeById(id!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { data ->
                        Glide.with(this)
                            .load("https://bikebuka.herokuapp.com/api/uploads/${data.BikeImage}")
                            .into(bikeImage)
                        bikeName.text = data.BikeType
                        Timber.d(data.toString())
                    },
                    { t -> Timber.e(t.localizedMessage) })
        )
    }
}