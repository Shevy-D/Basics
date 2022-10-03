package com.shevy.basics.presentation

import android.app.DownloadManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import com.shevy.basics.R
import com.shevy.basics.databinding.ActivityDmactivityBinding
import java.io.FileNotFoundException

class DMActivity : AppCompatActivity() {
    private lateinit var downloadManager: DownloadManager
    private lateinit var fileName: String
    private var downLoadId: Long = 0
    lateinit var binding: ActivityDmactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeDownloadManager()

        binding.downloadBtn.setOnClickListener { downloadFile() }
        binding.openDownloadBtn.setOnClickListener { openOurDownload() }
        binding.viewDownloadsBtn.setOnClickListener { viewAllDownloads() }
        binding.deleteBtn.setOnClickListener { deleteDownloadedFile() }
    }

    private fun initializeDownloadManager() {
        downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        fileName = "Voyager"
    }

    private fun downloadFile() {
        val request: DownloadManager.Request =
            DownloadManager.Request(Uri.parse("https://raw.githubusercontent.com/Oclemy/SampleJSON/master/spacecrafts/voyager.jpg"))
        request
            .setTitle("Voyager")
            .setDescription("File is downloading...")
            .setDestinationInExternalFilesDir(
                this,
                Environment.DIRECTORY_DOWNLOADS, fileName
            )
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downLoadId = downloadManager.enqueue(request)
    }

    private fun openOurDownload() {
        try {
            downloadManager.openDownloadedFile(downLoadId)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Toast.makeText(this, "The File is Not Found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewAllDownloads() {
        val intent = Intent()
        intent.action = DownloadManager.ACTION_VIEW_DOWNLOADS
        startActivity(intent)
    }

    private fun deleteDownloadedFile() {
        downloadManager.remove(downLoadId)
    }
}