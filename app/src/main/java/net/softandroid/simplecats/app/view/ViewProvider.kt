package net.softandroid.teststproject.app.view

import android.view.View

interface ViewProvider<T : View> {

    val view: T

}