package com.example.capstoneexpert.date

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneexpert.core.data.Resource
import com.example.capstoneexpert.date.databinding.ActivityDateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DateActivity : AppCompatActivity() {

    private val dateViewModel: DateViewModel by viewModel()
    private lateinit var binding: ActivityDateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(dateModule)

        getNewsData()
    }

    private fun getNewsData() {
        dateViewModel.news.observe(this) { news ->
            if (news != null) {
                when (news) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvDate.text = "This is Author of Article number one ${news.data?.get(0)?.author}"
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = news.message
                    }
                }
            }
        }
    }
}