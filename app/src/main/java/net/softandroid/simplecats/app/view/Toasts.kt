package net.softandroid.simplecats.app.view

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Context.toast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Activity.toast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
