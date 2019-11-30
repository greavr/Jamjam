package com.example.jamjam

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.google.cloud.storage.StorageOptions

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        //Set global functions here
        var mapid = "AAAA"
        var gcs_bucket_value = "jamboard-public"
        var gcs_bucket_path = "AppUploads"
        var SiteUrl = "google.com"
        var gcs_project = "corp-ce-jamboard"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Generate Random ID on load
        reset_id()

    }

    fun quickstart(bucketName: String) {
        // [START storage_quickstart]
        // import com.google.cloud.storage.StorageOptions
        val storage = StorageOptions.getDefaultInstance().service
        val bucket = storage.get(bucketName) ?: error("Bucket $bucketName does not exist.")

        println("Listing all blobs in bucket $bucketName:")
        bucket.list().iterateAll().forEach { blob ->
            println("${blob.name} (content-type: ${blob.contentType}, size: ${blob.size})")
        }
        // [END storage_quickstart]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        //Show Map
        if (id == R.id.action_viewmap) {
            this.startActivity(Intent(this,ViewMap::class.java))
            return true
        }

        //Show Settings
        if (id == R.id.action_settings) {
            this.startActivity(Intent(this,Setting::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun reset_id(){
        //Function to return a 4 char random string
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZ"

        var newID =  (1..4)
            .map { allowedChars.random() }
            .joinToString("")

        //Set global value
        mapid = newID
    }
}
