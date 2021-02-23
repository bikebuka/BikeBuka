package com.bikebuka.bikebuka.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.filterdialog.FilterDialog
import com.barisatalay.filterdialog.model.DialogListener
import com.bikebuka.bikebuka.R
import com.bikebuka.bikebuka.databinding.ActivityHomeBinding
import com.bikebuka.bikebuka.domain.Location
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
    lateinit var locations: MutableList<Location>
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
        homeAdapter.itemListener = { _, item, _ ->
//            val intent = Intent(this, BikeDetailActivity::class.java)
//            intent.putExtra("id", item.BikeId)
//            startActivity(intent)
        }
        locations = ArrayList()
        locations.add(Location(1, "Jkuat Student Center"))
        locations.add(Location(1, "Jkuat Gate A"))
        locations.add(Location(1, "Jkuat Gate B"))
        locations.add(Location(1, "Jkuat Gate C"))
        locations.add(Location(1, "Gachororo"))
        locations.add(Location(1, "Highpoint"))
        locations.add(Location(1, "Juja City Mall"))
        locations.add(Location(1, "Juja Market"))

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_us -> {
                true
            }
            R.id.filter -> {
                val filterDialog = FilterDialog(this)
                filterDialog.toolbarTitle = "Pickup Location"
                filterDialog.searchBoxHint = "Pick up location"
                filterDialog.setList(locations)

                filterDialog.show("id", "title",
                    DialogListener.Single { selectedItem ->
                        homeRecyclerView.visibility = View.GONE
                        shimmerFrameLayout.visibility = View.VISIBLE
                        shimmerFrameLayout.startShimmerAnimation()
                        compositeDisposable.add(
                            viewModel.filterBikeByLocation(selectedItem.name)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe({
                                    homeAdapter.addItems(it.data)
                                    handleShimmerEffect()
                                }, { t -> Timber.e(t.localizedMessage) })
                        )
                        filterDialog.dispose()
                    })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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