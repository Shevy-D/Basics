package com.shevy.basics

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.shevy.basics.databinding.ActivitySecondBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class SecondActivityFromGifApp : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailImageView = binding.detailImageView
        val downloadButton = binding.downloadButton

        url = intent.getStringExtra("url").toString()
        Glide.with(this).load(url).into(detailImageView)

        downloadButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                askPermissions()
            } else {
                downloadImage(url)
            }
        }
    }

    private fun askPermissions() {
/*        Проверка текущего статуса разрешения выполняется методом
          checkSelfPermission Этот метод возвращает перечисление
          Android.Content.PM.Permission , которое имеет
          одно из двух значений:

          Permission.Granted — указанное разрешение было предоставлено.
          Permission.Denied — указанное разрешение не было предоставлено.*/

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED   //==PackageManager.PERMISSION_DENIED
        ) { /*
            Метод shouldShowRequestPermissionRationale передаете название разрешения,
            а он вам в виде boolean ответит, надо ли показывать объяснение для пользователя.

            Чтобы решить, надо ли показывать объяснение пользователю. Если не надо, то
            делаете запрос разрешения. А если надо, то показываете ваш диалог с
            объяснением, а после этого диалога делаете запрос разрешения.*/
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                AlertDialog.Builder(this)
                    .setTitle("Permission required")
                    .setMessage("Permission required to save photos from the Web.")
                    .setPositiveButton("Accept") { dialog, id ->
                        /*
                        Если разрешения нет, то нам надо его запросить. Это выполняется методом
                        ActivityCompat.RequestPermissions(activity: Activity,
                        permissions: Array, requestCode: Int)
                        Для этого метода требуются следующие параметры:

                        activity — это действие, запрашивающее разрешения и информирующее
                        Android о результатах.
                        permissions — список запрашиваемых разрешений.
                        requestCode — целочисленное значение, которое используется для
                        сопоставления результатов запроса разрешения на RequestPermissions вызов.
                        Значение должно быть больше нуля.
                         */
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                        )
                        finish()
                    }
                    .setNegativeButton("Deny") { dialog, id ->
                        dialog.cancel()
                    }
                    .show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                )
            }
        } else {
            downloadImage(url)
        }
    }

    //Получаем разрешение пользователя
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

/*      Проверяем, что requestСode тот же, что мы указывали в requestPermissions.
        В массиве permissions придут название разрешений, которые мы запрашивали.
        В массиве grantResults придут ответы пользователя на запросы разрешений.
*/
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    downloadImage(url)
                }
                return
            }
            else -> {}
        }
    }

    var msg: String? = ""
    var lastMsg = ""

    private fun downloadImage(url: String) {
/*      DIRECTORY_PICTURES – это стандартный каталог, в котором
        размещаются изображения, доступные пользователю.*/
        val directory = File(Environment.DIRECTORY_PICTURES)

        if (!directory.exists()) {
            directory.mkdir()  //make directory
        }

        //Метод getSystemService используется для получения различных системных служб
        /* Класс android.app.DownloadManager, который является системным сервисом
        и позволяет загружать файлы в фоновом режиме (начиная с API 9).
        Доступ к данному менеджеру осуществляется через вызов метода
        getSystemService(String) с константой DOWNLOAD_SERVICE.*/
        val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        //Класс Request может устанавливать некоторые свойства загрузки
        val request = DownloadManager.Request(Uri.parse(url)).apply {
            //Указываем для выполнения операции загрузки в состоянии WIFI или MOBILE
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                //Запретить операцию загрузки в режиме роуминга
                .setAllowedOverRoaming(false)
                //Устанавливаем заголовок уведомления
                .setTitle(url.substring(url.lastIndexOf("/") + 1))
                //Устанавливаем описание уведомления
                .setDescription("")

                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

                //Установливаем пункт назначения во внешнем публичном каталоге
                .setDestinationInExternalPublicDir(
                    //Место хранения загруженных файлов Папка загрузки SD-карты
                    directory.toString(),
                    //Название файла
                    url.substring(url.lastIndexOf("/") + 1)
                )
        }

        //Ставим в очередь загрузку, используя enqueue() метод. Это вернет нам идентификатор загрузки.
        val downloadId = downloadManager.enqueue(request)
/*      В процессе загрузки DownloadManager соответствующие данные сохраняются в
        базе данных. Если вам нужно получить соответствующую информацию о данных,
        вам необходимо реализовать ее через Query. Класс Query может запрашивать
        текущий прогресс загрузки, адрес загрузки, каталог хранения файлов и др. данные.*/
        val query = DownloadManager.Query().setFilterById(downloadId)

        lifecycleScope.launch {
            var downloading = true
            while (downloading) {
                //Получаем курсор - набор строк в табличном виде
                val cursor: Cursor = downloadManager.query(query)
                /*Для доступа курсора вы должны использовать метод moveToFirst(),
                так как курсор размещается перед первой строкой
                moveToFirst() - перемещает курсор на первую строку в результате запроса */
                cursor.moveToFirst()
                //С помощью метода getColumnIndex() с указанием имени колонки мы можем
                //извлечь данные, которые хранятся в них.
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                msg = statusMessage(url, directory, status)
                if (msg != lastMsg) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SecondActivityFromGifApp, msg, Toast.LENGTH_SHORT)
                            .show()
                    }
                    lastMsg = msg ?: ""
                }
                //Курсор обязательно следует закрывать методом close() для освобождения памяти.
                cursor.close()

                delay(1000)
            }
        }
    }

    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download has been failed, please try again"
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> "Image downloaded successfully in $directory" + File.separator + url.substring(
                url.lastIndexOf("/") + 1
            )
            else -> "There is nothing to download"
        }
        return msg
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1
    }
}