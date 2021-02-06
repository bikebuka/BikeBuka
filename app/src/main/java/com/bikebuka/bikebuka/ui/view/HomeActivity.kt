package com.bikebuka.bikebuka.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bikebuka.bikebuka.R
import com.bikebuka.bikebuka.databinding.ActivityHomeBinding
import com.bikebuka.bikebuka.ui.viewmodel.HomeViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    lateinit var homeRecyclerView: RecyclerView
    lateinit var binding: ActivityHomeBinding
    lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private val homeAdapter by lazy {
        HomeAdapter()
    }
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout)

        homeRecyclerView = findViewById(R.id.home_recycler)
        homeAdapter.listener = { _, item, _ ->
            BottomSheetDialog().apply {
                show(supportFragmentManager, "MainActivity.kt")
                Timber.d(item.BikeDescription)
            }
        }
//        homeAdapter.itemListener = { _, item, _ ->
//            val bundle = transformationLayout.withActivity(this, "TransformationParams")
//            val intent = Intent(this, BikeDetailActivity::class.java)
//            intent.putExtra("TransformationParams", transformationLayout.getParcelableParams())
//            startActivity(intent, bundle)
//        }

        compositeDisposable.add(
            viewModel.getBikes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data ->
                        data.toString()
                        homeAdapter.addItems(data.data)
                        handleShimmerEffect()
                    },
                    { t -> Timber.e("Error======> ${t.localizedMessage}") })
        )
    }

    private fun handleShimmerEffect() {
        shimmerFrameLayout.stopShimmerAnimation()
        shimmerFrameLayout.visibility = View.GONE
        homeRecyclerView.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = homeAdapter
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