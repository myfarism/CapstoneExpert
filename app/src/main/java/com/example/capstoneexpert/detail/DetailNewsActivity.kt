package com.example.capstoneexpert.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.capstoneexpert.R
import com.example.capstoneexpert.core.domain.model.News
import com.example.capstoneexpert.databinding.ActivityDetailNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding
    private val detailNewsViewModel: DetailNewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailNews = getParcelableExtra(intent, EXTRA_DATA, News::class.java)
        showDetailNews(detailNews)

        binding.contentDetailNews.btnOpenArticle.setOnClickListener {
            detailNews?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "URL artikel tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailNews(detailNews: News?) {
        detailNews?.let {
            supportActionBar?.title = detailNews.title
            binding.contentDetailNews.tvDetailTitle.text = detailNews.title
            binding.contentDetailNews.tvDetailAuthor.text = "Author: ${detailNews.author ?: "Unknown"}"
            binding.contentDetailNews.tvDetailDescription.text = detailNews.description
            Glide.with(this@DetailNewsActivity)
                .load(detailNews.urlToImage)
                .apply(RequestOptions().centerCrop())
                .into(binding.ivDetailImage)

            var statusFavorite = detailNews.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailNewsViewModel.setFavoriteNews(detailNews, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
