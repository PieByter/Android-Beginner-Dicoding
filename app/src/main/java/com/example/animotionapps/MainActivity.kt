package com.example.animotionapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnimotion: RecyclerView
    private val listAnimotion = ArrayList<AnimotionData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnimotion = findViewById(R.id.rv_animotion)
        rvAnimotion.setHasFixedSize(true)

        listAnimotion.addAll(getlistAnimotionDatas())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getlistAnimotionDatas(): List<AnimotionData> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listAnimotion = mutableListOf<AnimotionData>()

        for (i in dataName.indices) {
            if (i < dataDescription.size && i < dataPhoto.size) {
                val animotionData = AnimotionData(
                    dataName[i],
                    dataDescription[i],
                    dataPhoto[i]
                )
                listAnimotion.add(animotionData)
            } else {
                Log.e("MainActivity", "Index out of bounds at position $i")
            }
        }
        return listAnimotion
    }

    private fun showRecyclerList() {
        rvAnimotion.layoutManager = LinearLayoutManager(this)
        val listAnimotionAdapter = ListAdapter(listAnimotion)
        rvAnimotion.adapter = listAnimotionAdapter

        listAnimotionAdapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: AnimotionData) {
                showSelectedAnime(data)
            }
        })
    }

    private fun showSelectedAnime(animotionData: AnimotionData) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("animotion_detail", animotionData)
        }
        startActivity(intent)
    }
}
