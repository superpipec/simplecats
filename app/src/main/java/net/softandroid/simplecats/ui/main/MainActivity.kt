package net.softandroid.simplecats.ui.main

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.caregenie.common.view.DataBindingActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.softandroid.simplecats.R
import net.softandroid.simplecats.app.view.toast
import net.softandroid.simplecats.base.main.MainNavigator
import net.softandroid.simplecats.base.main.MainViewModel
import net.softandroid.simplecats.base.main.controllers.MainListItemsController
import net.softandroid.simplecats.databinding.ActivityMainBinding
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest

const val STORAGGE_PERMISSION = 12332

class MainActivity : DataBindingActivity<ActivityMainBinding>() {
    private val navigator: MainNavigator by currentScope.inject {
        parametersOf(this)
    }

    override val layoutId = R.layout.activity_main
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mainToolbar)
        binding.vm = viewModel

        viewModel.getItems()
        currentScope.get<MainListItemsController> { parametersOf(this) }

        setupListeners()
    }

    private fun setupListeners() {
        mainShowMore.setOnClickListener {
            viewModel.getItems()
        }
        viewModel.saveListener = { toast(getString(R.string.save_success)) }
        viewModel.errorSaveListener = { toast(getString(R.string.error_save_favourites)) }
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
            R.id.mainMnuFavourites -> navigator.toFavourites()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
}