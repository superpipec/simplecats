package com.caregenie.common.view

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.ViewParent
import android.widget.EditText
import android.widget.ScrollView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Common bindable [AppCompatActivity] implementation.
 * Helps to setup view data binding on activity
 * */
abstract class DataBindingActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

    }

}
