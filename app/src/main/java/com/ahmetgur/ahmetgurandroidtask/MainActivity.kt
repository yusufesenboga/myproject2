package com.ahmetgur.ahmetgurandroidtask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetgur.ahmetgurandroidtask.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var simpsonAdapter: SimpsonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getSimpsons()
            } catch (e: IOException) {
                Log.e(TAG, "IOException")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                simpsonAdapter.simpsons = response.body()!!.RelatedTopics
                binding.progressBar.isVisible = false
            }
        }
    }

    private fun setupRecyclerView() = binding.rvSimpsons.apply {
        simpsonAdapter = SimpsonAdapter()
        adapter = simpsonAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}