package net.softandroid.teststproject.ui.main.viewproviders

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.softandroid.simplecats.ui.main.MainActivity
import net.softandroid.teststproject.app.view.ViewProvider

class MainRvProvider(
    private val activity: MainActivity
) : ViewProvider<RecyclerView> {
    override val view: RecyclerView
        get() = activity.mainRvItems
}