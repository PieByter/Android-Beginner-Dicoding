package com.example.animotionapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.animotionapps.databinding.ActivityDetailBinding

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Details"
        }

        val animotionData = intent.getParcelableExtra<AnimotionData>("animotion_detail")

        binding.apply {
            tvDetailName.text = animotionData?.name
            tvDetailDescription.text = animotionData?.description
            ratingDescription.text = animotionData?.rating
            jlhEpsDescription.text = animotionData?.episodes

            animotionData?.photo?.let {
                Glide.with(this@DetailActivity)
                    .load(it)
                    .into(ivDetailPhoto)
            }

            actionShare.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Check out this anime: ${animotionData?.name}" +
                                "\nDetails: ${animotionData?.description}" +
                                "\nRating: ${animotionData?.rating}" +
                                "\nTotal Episodes: ${animotionData?.episodes}"
                    )
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
