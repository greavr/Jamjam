package com.example.jamjam

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import com.example.jamjam.MainActivity.Companion

class Setting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //Load Default Values
        this.gcs_bucketname.setText(Companion.gcs_bucket_value)
        this.gcs_path.setText(Companion.gcs_bucket_path)
        this.site_url.setText(Companion.SiteUrl)
        this.txt_currentID.text = "Current ID: " + Companion.mapid
        this.gcs_project.setText(Companion.gcs_project)

        //Save the build#
        this.lbl_version.text = "Version: " + BuildConfig.VERSION_NAME

        this.btnSaveValues.setOnClickListener {
            Companion.gcs_bucket_value = this.gcs_bucketname.text.toString()
            Companion.gcs_bucket_path = this.gcs_path.toString()
            Companion.SiteUrl = this.site_url.toString()
            Companion.gcs_project = this.gcs_project.toString()

            Toast.makeText(this,"Saved Target '" + Companion.gcs_bucket_value + "' as GCS Bucket",Toast.LENGTH_SHORT).show()
        }
    }

}