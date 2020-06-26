package net.softandroid.simplecats.ui.favourites

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.caregenie.common.view.DataBindingActivity
import kotlinx.android.synthetic.main.activity_favourites.*
import net.softandroid.simplecats.R
import net.softandroid.simplecats.base.favourites.FavouritesViewModel
import net.softandroid.simplecats.base.favourites.controllers.FavsListItemsController
import net.softandroid.simplecats.databinding.ActivityFavouritesBinding
import net.softandroid.simplecats.ui.main.STORAGGE_PERMISSION
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest

class FavouritesActivity : DataBindingActivity<ActivityFavouritesBinding>() {

    override val layoutId = R.layout.activity_favourites
    private val viewModel: FavouritesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()

        binding.vm = viewModel
        viewModel.getFavs()
        currentScope.get<FavsListItemsController> { parametersOf(this) }
    }

    @AfterPermissionGranted(STORAGGE_PERMISSION)
    fun downloadImage() {
        if (hasStoragePermission()) {
            viewModel.downloadFile()
        } else {
            EasyPermissions.requestPermissions(
                PermissionRequest.Builder(
                    this,
                    STORAGGE_PERMISSION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).build()
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hasStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private fun initToolbar() {
        this.setSupportActionBar(favsToolbar)
        this.supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_white_24)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {

        fun buildIntent(context: Context) =
            Intent(context, FavouritesActivity::class.java)
    }

}