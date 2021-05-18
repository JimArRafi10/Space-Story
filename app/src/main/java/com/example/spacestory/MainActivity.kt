package com.example.spacestory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    var storyTitles= arrayOf<String>()
    var storyContents= arrayOf<String>()
    var storyImages=arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar2)
        val toggle=ActionBarDrawerToggle(this,drawerLayout,toolbar2,R.string.open,R.string.close)

        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationview.setNavigationItemSelectedListener (this)

        storyTitles= resources.getStringArray(R.array.storyTitles)
        storyContents= resources.getStringArray(R.array.storyContents)
        storyImages= resources.getStringArray(R.array.storyImages)


        val adapter = ItemAdapter(storyTitles,storyContents,storyImages)
        storylist.layoutManager = LinearLayoutManager(this)
        storylist.adapter = adapter

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        drawerLayout.closeDrawer(GravityCompat.START)
        if(item.itemId == R.id.random){
            val randPosition = Random.nextInt(0,storyTitles.size)
            val intent = Intent(applicationContext,Details::class.java)
            intent.putExtra("storyTitle",storyTitles[randPosition])
            intent.putExtra("storyContent",storyContents[randPosition])
            intent.putExtra("storyImage",storyImages[randPosition])
            startActivity(intent)

        }

        return true
    }
}