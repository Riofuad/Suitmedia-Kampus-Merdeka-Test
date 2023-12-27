package com.riofuad.suitmediakmtest.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.riofuad.suitmediakmtest.databinding.ActivityThirdScreenBinding
import com.riofuad.suitmediakmtest.ui.adapter.LoadingStateAdapter
import com.riofuad.suitmediakmtest.ui.adapter.UserAdapter
import com.riofuad.suitmediakmtest.ui.viewmodel.UserViewModel
import com.riofuad.suitmediakmtest.ui.viewmodel.UserViewModelFactory
import java.util.*
import kotlin.concurrent.schedule

class ThirdScreenActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: ActivityThirdScreenBinding
    private val adapter = UserAdapter()
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }

            swipeRefresh.setOnRefreshListener {
                onRefresh()
            }
            rvUser.layoutManager = LinearLayoutManager(this@ThirdScreenActivity)
            rvUser.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
            viewModel.user.observe(this@ThirdScreenActivity) {
                adapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onRefresh() {
        binding.swipeRefresh.isRefreshing = true
        adapter.refresh()
        Timer().schedule(2000) {
            binding.swipeRefresh.isRefreshing = false
            binding.rvUser.smoothScrollToPosition(0)
        }
    }
}