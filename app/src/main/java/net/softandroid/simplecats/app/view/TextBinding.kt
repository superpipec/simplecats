package net.softandroid.teststproject.app.view

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

@BindingAdapter("bindResText")
fun bindResText(textView: TextView, @StringRes res: Int?) {
    if (res == null) {
        return
    }
    textView.text = textView.resources.getText(res)
}