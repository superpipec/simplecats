package net.softandroid.simplecats.base.download

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import net.softandroid.simplecats.R

class FileDownloader(
    private val context: Context
) {

    fun download(url: String) {
        val name = "stubname." + url.split(".").last() // stub file name with extension
        val request = DownloadManager.Request(Uri.parse(url))
        request.setDescription(context.getString(R.string.common_download_title, name))
        request.setTitle(context.getString(R.string.common_download_title, name))
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(getDownloadsDir(), name)
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

    private fun getDownloadsDir() =
        Environment.DIRECTORY_DOWNLOADS
}