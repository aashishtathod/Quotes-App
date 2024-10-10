package com.aashishtathod.dev.quotesapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.aashishtathod.dev.quotesapp.R
import com.aashishtathod.dev.quotesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(QuoteViewModel::class.java)
    }

    private val adapter by lazy {
        QuotesAdapter()
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpUI()
        setUpObservers()

    }

    private fun setUpUI() {
        binding.rvQuotes.adapter = adapter
        binding.rvQuotes.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiState.collect {
                    when (it) {
                        is QuoteUiState.None -> {
                            // do nothing
                        }

                        is QuoteUiState.Loading -> {
                            Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                        }

                        is QuoteUiState.Success -> {
                            adapter.submitData(this@MainActivity.lifecycle, it.data)
                        }

                        is QuoteUiState.Error -> {
                            Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                adapter.loadStateFlow.collectLatest { loadStates ->
                    when (loadStates.refresh) {
                        is LoadState.Error -> {
                            binding.refreshProgressBar.isVisible = false
                            Toast.makeText(this@MainActivity, "Refresh Error", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is LoadState.Loading -> binding.refreshProgressBar.isVisible = true
                        is LoadState.NotLoading -> binding.refreshProgressBar.isVisible = false
                    }

                    when (loadStates.append) {
                        is LoadState.Error -> {
                            binding.appendProgressBar.isVisible = false
                            Toast.makeText(this@MainActivity, "Append Error", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is LoadState.Loading -> binding.appendProgressBar.isVisible = true
                        is LoadState.NotLoading -> binding.appendProgressBar.isVisible = false
                    }

                    when (loadStates.prepend) {
                        is LoadState.Error -> {
                            binding.prependProgressBar.isVisible = false
                            Toast.makeText(this@MainActivity, "Prepend Error", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is LoadState.Loading -> binding.prependProgressBar.isVisible = true
                        is LoadState.NotLoading -> binding.prependProgressBar.isVisible = false

                    }
                }
            }
        }
    }
}